/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Dec 24, 2018
 */
package demos.bits;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.ProblemInnerProduct;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;

public class ProblemInnerProductDemo extends Problem implements IProblem
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

		INaturalNumber X = new NaturalNumber("X");
		INaturalNumber Y = new NaturalNumber("Y");

		/**
		 * Create problems which constrain the values of these Saffron objects:
		 */

		IProblem[] P = new IProblem[]
		{ new NaturalNumberFixer(X, 0), new NaturalNumberFixer(X, 1), new NaturalNumberFixer(X, 1),
				new NaturalNumberFixer(X, 0), new NaturalNumberFixer(X, 1) };
		IProblem[] Q = new IProblem[]
		{ new NaturalNumberFixer(Y, 1), new NaturalNumberFixer(Y, 2), new NaturalNumberFixer(Y, 3),
				new NaturalNumberFixer(Y, 4), new NaturalNumberFixer(Y, 5) };

		/**
		 * Create the IProblem of satisfying all of these constraining problems:
		 */

		IProblemMessage s = new Conjunction(new ProblemInnerProduct(P, Q), new NaturalNumberFixer(X, 1))
				.findModel(Problem.defaultSolver());

		/**
		 * Solve the IProblem:
		 */

		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("X=" + X);
			System.out.println("Y=" + Y);
		}
		else
			System.out.println("No solution.");
	}
}
