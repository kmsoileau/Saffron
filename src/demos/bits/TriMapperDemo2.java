/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Dec 29, 2018
 */
package demos.bits;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.ProblemTriplet;
import bits.TriMapper;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;

public class TriMapperDemo2
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
		INaturalNumber Z = new NaturalNumber("Z");

		ProblemTriplet[] array = new ProblemTriplet[]
		{ new ProblemTriplet(new NaturalNumberFixer(X, 0), new NaturalNumberFixer(Y, 0), new NaturalNumberFixer(Z, 0)),
				new ProblemTriplet(new NaturalNumberFixer(X, 0), new NaturalNumberFixer(Y, 1),
						new NaturalNumberFixer(Z, 1)),
				new ProblemTriplet(new NaturalNumberFixer(X, 0), new NaturalNumberFixer(Y, 2),
						new NaturalNumberFixer(Z, 2)),
				new ProblemTriplet(new NaturalNumberFixer(X, 0), new NaturalNumberFixer(Y, 3),
						new NaturalNumberFixer(Z, 3)),
				new ProblemTriplet(new NaturalNumberFixer(X, 1), new NaturalNumberFixer(Y, 0),
						new NaturalNumberFixer(Z, 1)),
				new ProblemTriplet(new NaturalNumberFixer(X, 1), new NaturalNumberFixer(Y, 1),
						new NaturalNumberFixer(Z, 0)),
				new ProblemTriplet(new NaturalNumberFixer(X, 1), new NaturalNumberFixer(Y, 2),
						new NaturalNumberFixer(Z, 3)),
				new ProblemTriplet(new NaturalNumberFixer(X, 1), new NaturalNumberFixer(Y, 3),
						new NaturalNumberFixer(Z, 2)),
				new ProblemTriplet(new NaturalNumberFixer(X, 2), new NaturalNumberFixer(Y, 0),
						new NaturalNumberFixer(Z, 2)),
				new ProblemTriplet(new NaturalNumberFixer(X, 2), new NaturalNumberFixer(Y, 1),
						new NaturalNumberFixer(Z, 3)),
				new ProblemTriplet(new NaturalNumberFixer(X, 2), new NaturalNumberFixer(Y, 2),
						new NaturalNumberFixer(Z, 0)),
				new ProblemTriplet(new NaturalNumberFixer(X, 2), new NaturalNumberFixer(Y, 3),
						new NaturalNumberFixer(Z, 1)),
				new ProblemTriplet(new NaturalNumberFixer(X, 3), new NaturalNumberFixer(Y, 0),
						new NaturalNumberFixer(Z, 3)),
				new ProblemTriplet(new NaturalNumberFixer(X, 3), new NaturalNumberFixer(Y, 1),
						new NaturalNumberFixer(Z, 2)),
				new ProblemTriplet(new NaturalNumberFixer(X, 3), new NaturalNumberFixer(Y, 2),
						new NaturalNumberFixer(Z, 1)),
				new ProblemTriplet(new NaturalNumberFixer(X, 3), new NaturalNumberFixer(Y, 3),
						new NaturalNumberFixer(Z, 0)) };

		/**
		 * Create problems which constrain the values of these Saffron objects:
		 */

		/**
		 * Create the IProblem of satisfying all of these constraining problems:
		 */

		TriMapper grouper = new TriMapper(array);

		IProblem problem = new Conjunction(new NaturalNumberFixer(Z, 1), grouper);

		/**
		 * Solve the IProblem:
		 */

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("X = " + X);
			System.out.println("Y = " + Y);
			System.out.println("Z = " + Z);

		}
		else
			System.out.println("No solution.");
	}
}
