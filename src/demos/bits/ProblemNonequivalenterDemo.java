/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 12, 2019
 */
package demos.bits;

import bits.BooleanLiteral;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.ProblemNonequivalenter;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;

public class ProblemNonequivalenterDemo
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

		INaturalNumber A = new NaturalNumber();
		INaturalNumber B = new NaturalNumber();

		/**
		 * Create problems which constrain the values of these Saffron objects:
		 */

		IProblem p1 = new NaturalNumberFixer(A, 2);
		IProblem p2 = new NaturalNumberFixer(B, 2);

		/**
		 * Create the IProblem of satisfying all of these constraining problems:
		 */

		IProblem problem = new ProblemNonequivalenter(p1, p2);

		/**
		 * Solve the IProblem:
		 */

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());

			System.out.println("A = " + A);
			System.out.println("B = " + B);
		}
		else
			System.out.println("No solution.");
	}
}
