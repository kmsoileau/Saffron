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
import bits.BooleanVariable;
import bits.Conjunction;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.ProblemTriplet;
import bits.TriMapper;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;

public class TriMapperDemo
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
		{ new ProblemTriplet(new NaturalNumberFixer(X, 0), new NaturalNumberFixer(Y, 1), new NaturalNumberFixer(Z, 1)),
				new ProblemTriplet(new NaturalNumberFixer(X, 1), new NaturalNumberFixer(Y, 3),
						new NaturalNumberFixer(Z, 2)),
				new ProblemTriplet(new NaturalNumberFixer(X, 2), new NaturalNumberFixer(Y, 13),
						new NaturalNumberFixer(Z, 3)) };
		IBooleanVariable[] b = new IBooleanVariable[array.length - 1];
		for (int i = 0; i < array.length - 1; i++)
			b[i] = BooleanVariable.getBooleanVariable("b" + i);

		/**
		 * Create problems which constrain the values of these Saffron objects:
		 */

		TriMapper mapper = new TriMapper(array, b);

		/**
		 * Create the IProblem of satisfying all of these constraining problems:
		 */

		IProblem problem = new Conjunction(new NaturalNumberFixer(Z, 1), mapper);

		System.out.println(problem);

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

			for (int i = 0; i < b.length; i++)
				System.out.println(b[i].getValue());
		}
		else
			System.out.println("No solution.");
	}
}
