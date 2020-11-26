package showcase.sendmoremoney;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 22, 2019
 */

import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberAdder;
import naturalnumbers.NaturalNumberDigiter;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.NaturalNumberMultiplier;
import utility.Clock;

public class SENDMOREMONEY2
{
	public static void main(String[] args) throws Exception
	{
		Clock c = new Clock("C");
		c.start();
		/**
		 * Set Java variables:
		 */

		/**
		 * Set globals:
		 */

		NaturalNumber.setLargestNaturalNumber(99999);

		/**
		 * Create Saffron objects and arrays:
		 */

		INaturalNumber A = new NaturalNumber("A");
		INaturalNumber B = new NaturalNumber("B");
		INaturalNumber C = new NaturalNumber("C");
		INaturalNumber _72 = new NaturalNumber("_72");
		INaturalNumber _10000 = new NaturalNumber("_10000");
		INaturalNumber _6790 = new NaturalNumber("_6790");
		INaturalNumber X2 = new NaturalNumber("X2");
		INaturalNumber X3 = new NaturalNumber("X3");
		INaturalNumber X4 = new NaturalNumber("X4");

		/**
		 * Create problems which constrain the values of these Saffron objects:
		 */

		IProblem p1 = new NaturalNumberFixer(_72, 72);
		IProblem p1x = new NaturalNumberFixer(_10000, 10000);
		IProblem p1xx = new NaturalNumberFixer(_6790, 6790);
		IProblem p2 = new NaturalNumberDigiter(A);
		IProblem p3 = new NaturalNumberDigiter(B);
		IProblem p4 = new NaturalNumberMultiplier(A, _10000, X2);
		IProblem p5 = new NaturalNumberAdder(X2, B, X3);
		IProblem p5x = new NaturalNumberAdder(X3, _6790, X4);
		IProblem p6 = new NaturalNumberMultiplier(_72, C, X4);

		/**
		 * Create the IProblem of satisfying all of these constraining problems:
		 */

		IProblem problem = new Conjunction(new IProblem[]
		{ p1, p1x, p1xx, p2, p3, p4, p5, p5x, p6 });

		/**
		 * Solve the IProblem:
		 */

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());

			System.out.println("A = " + A);
			System.out.println("B = " + B);
			System.out.println("C = " + C);
		}
		else
			System.out.println("No solution.");
		c.stop();

		System.out.println(c.getTotalElapsedTime());
	}
}