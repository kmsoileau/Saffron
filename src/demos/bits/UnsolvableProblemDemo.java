/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Dec 25, 2018
 */
package demos.bits;

import bits.BooleanLiteral;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

public class UnsolvableProblemDemo
{
	public static void main(String[] args) throws Exception
	{
		IProblem p = Problem.unsolvableProblem();
		System.out.println(p);

		IProblemMessage s = p.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE
				&& s.getLiterals().size() > 0)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("Solved");
		}
		else
			System.out.println("No solution.");
	}
}
