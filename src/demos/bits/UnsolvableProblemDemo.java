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
import bits.EnhancedProblem;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

public class UnsolvableProblemDemo
{
	public static void main(String[] args) throws Exception
	{
		/**
		 * Set Java variables:
		 */

		/**
		 * Set globals:
		 */

		/**
		 * Create Saffron objects and arrays:
		 */

		/**
		 * Create problems which constrain the values of these Saffron objects:
		 */

		IProblem p = EnhancedProblem.unsolvableProblem();
		System.out.println(p);

		/**
		 * Create the IProblem of satisfying all of these constraining problems:
		 */

		/**
		 * Solve the IProblem:
		 */

		IProblemMessage s = p.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("Solved");
		}
		else
			System.out.println("No solution.");
	}
}
