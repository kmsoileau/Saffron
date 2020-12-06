package demos.bits;

import bits.BitFixer;
import bits.BitUnequalizer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

public class BitUnequalizerDemo
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

		IBooleanVariable x = BooleanVariable.getBooleanVariable("x");
		IBooleanVariable y = BooleanVariable.getBooleanVariable("y");

		/**
		 * Create problems which constrain the values of these Saffron objects:
		 */

		IProblem fixx = new BitFixer(x, true);
		IProblem fixy = new BitFixer(y, false);
		IProblem bitUnequalizer1 = new BitUnequalizer(x, y);

		/**
		 * Create the IProblem of satisfying all of these constraining problems:
		 */

		IProblem problem = new Conjunction(fixx, fixy, bitUnequalizer1);

		/**
		 * Solve the IProblem:
		 */

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("x= " + x.getValue());
			System.out.println("y= " + y.getValue());
		}
		else
			System.out.println("No solution.");
	}
}