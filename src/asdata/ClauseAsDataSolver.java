package asdata;

import java.util.ArrayList;
import java.util.List;

import bits.BitFixer;
import bits.BitUnequalizer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Clause;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBooleanLiteral;
import bits.IBooleanVariable;
import bits.IClause;
import bits.IProblem;
import bits.Problem;

public class ClauseAsDataSolver extends Problem implements IProblem
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable A = BooleanVariable.getBooleanVariable("A");
		IBooleanVariable B = BooleanVariable.getBooleanVariable("B");
		ArrayList<IBooleanVariable> ary = new ArrayList<IBooleanVariable>();
		ary.add(A);
		ary.add(B);
		ClauseAsData.declare(ary);

		IClause[] clauses = new IClause[]
		{ Clause.newClause().or(A).nor(B), Clause.newClause().or(A).or(B) };

		IClauseAsData[] dataclauses = new IClauseAsData[]
		{ new ClauseAsData(), new ClauseAsData() };

		IProblem[] probs = new IProblem[]
		{
				new Conjunction(new ClauseAsDataFixer(dataclauses[0],
						clauses[0]), new ClauseAsDataSolver(dataclauses[0])),
				new Conjunction(new ClauseAsDataFixer(dataclauses[1],
						clauses[1]), new ClauseAsDataSolver(dataclauses[1])) };

		IProblem prob = new Conjunction(probs);
		System.out.println(prob);

		List<IBooleanLiteral> s = (List<IBooleanLiteral>) prob
				.findModel(Problem.defaultSolver());
		System.out.println("Reporting...");
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println(A);
			System.out.println(B);
		}
		else
			System.out.println("No solution.");
	}

	public ClauseAsDataSolver(IClauseAsData dataclauses) throws Exception
	{
		IProblem prob = null;
		for (IBooleanVariable bv : ClauseAsData.getVARIABLES())
			prob = new Disjunction(prob, new Conjunction(new BitFixer(
					dataclauses.occurrenceIndicator(bv), true),
					new BitUnequalizer(bv, dataclauses.barredIndicator(bv))));
		this.setClauses(prob.getClauses());
	}
}
