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
import asdata.ClauseAsDataNonsolver;
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

public class ClauseAsDataNonsolverDemo extends Problem implements IProblem
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable A = BooleanVariable.getBooleanVariable("A");
		IBooleanVariable B = BooleanVariable.getBooleanVariable("B");

		ArrayList<IBooleanVariable> ary = new ArrayList<IBooleanVariable>();
		ary.add(A);
		ary.add(B);
		ClauseAsData.declare(ary);

		IClause clause = Clause.newClause().nor(B);

		IClauseAsData dataclause = new ClauseAsData();

		IProblem prob = new Conjunction(new ClauseAsDataFixer(dataclause, clause),
				new ClauseAsDataNonsolver(dataclause));

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
