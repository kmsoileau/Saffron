/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 11, 2019
 */
package demos.asdata;

import java.util.ArrayList;

import asdata.ClauseAsData;
import asdata.ClauseAsDataFixer;
import asdata.ClauseAsDataSolver;
import asdata.IClauseAsData;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Clause;
import bits.Conjunction;
import bits.IBooleanVariable;
import bits.IClause;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

/**
 * 
 *
 */
public class ClauseAsDataSolverDemo extends Problem implements IProblem
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
		{ new Conjunction(new ClauseAsDataFixer(dataclauses[0], clauses[0]), new ClauseAsDataSolver(dataclauses[0])),
				new Conjunction(new ClauseAsDataFixer(dataclauses[1], clauses[1]),
						new ClauseAsDataSolver(dataclauses[1])) };

		IProblem prob = new Conjunction(probs);
		System.out.println(prob);

		IProblemMessage s = prob.findModel(Problem.defaultSolver());
		System.out.println("Reporting...");
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println(A);
			System.out.println(B);
		}
		else
			System.out.println("No solution.");
	}
}
