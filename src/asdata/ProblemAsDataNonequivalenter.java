package asdata;

import java.util.ArrayList;

import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Clause;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBooleanVariable;
import bits.IClause;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

public class ProblemAsDataNonequivalenter extends Problem implements IProblem
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable A = BooleanVariable.getBooleanVariable("A");
		IBooleanVariable B = BooleanVariable.getBooleanVariable("B");
		IBooleanVariable C = BooleanVariable.getBooleanVariable("C");
		IBooleanVariable D = BooleanVariable.getBooleanVariable("D");
		IBooleanVariable E = BooleanVariable.getBooleanVariable("E");
		IBooleanVariable F = BooleanVariable.getBooleanVariable("F");
		ArrayList<IBooleanVariable> ary = new ArrayList<IBooleanVariable>();
		ary.add(B);
		ary.add(A);
		ary.add(F);
		ary.add(E);
		ary.add(C);
		ary.add(D);
		ClauseAsData.declare(ary);
		IClauseAsData c1 = new ClauseAsData();
		IBooleanVariable[] bvarray = ClauseAsData.getVARIABLES().toArray(
				new IBooleanVariable[0]);
		IClause clause1 = Clause.randomClause(bvarray);
		IClauseAsData c2 = new ClauseAsData();
		IClause clause2 = Clause.randomClause(bvarray);
		IProblem prob1 = null;
		for (int i = 0; i < clause1.size(); i++)
			prob1 = new Conjunction(prob1, new ClauseAsDataFixer(c1, clause1));
		IProblem prob2 = null;
		for (int i = 0; i < clause2.size(); i++)
			prob2 = new Conjunction(prob2, new ClauseAsDataFixer(c2, clause2));
		IClauseAsData[] clauses = new IClauseAsData[]
		{ c1, c2 };
		IProblemAsData p = new ProblemAsData(clauses);
		IProblem prob = new Conjunction(prob1, prob2,
				new ProblemAsDataNonequivalenter(new ProblemAsData(c1),
						new ProblemAsData(c2)));
		IProblemMessage s = prob.findModel(Problem.defaultSolver());
		System.out.println("Reporting...");
		if (s.getStatus() == IProblemMessage.SATISFIABLE
				&& s.getLiterals().size() > 0)
		{
			BooleanLiteral.interpret(s.getLiterals());
			for (IClauseAsData cl : clauses)
				System.out.println(cl);
			for (IBooleanVariable bv : ClauseAsData.getVARIABLES())
				System.out.println(bv);
		}
		else
			System.out.println("No solution.");

		System.out.println(p.toProblem());
	}

	public ProblemAsDataNonequivalenter(IProblemAsData p1, IProblemAsData p2)
			throws Exception
	{
		/*
		 * There exists a solution to p1 that doesn't solve p2, or there exists
		 * a solution to p2 that doesn't solve p1
		 */
		IProblem prob = new Disjunction(new Conjunction(
				new ProblemAsDataSolver(p1), new ProblemAsDataNonsolver(p2)),
				new Conjunction(new ProblemAsDataNonsolver(p1),
						new ProblemAsDataSolver(p2)));
		this.setClauses(prob.getClauses());
	}
}
