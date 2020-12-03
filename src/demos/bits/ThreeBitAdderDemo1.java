package demos.bits;

import bits.BitFixer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.ThreeBitAdder;

public class ThreeBitAdderDemo1
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

		IBooleanVariable w = BooleanVariable.getBooleanVariable("w");
		IBooleanVariable x = BooleanVariable.getBooleanVariable("x");
		IBooleanVariable y = BooleanVariable.getBooleanVariable("y");
		IBooleanVariable z = BooleanVariable.getBooleanVariable("z");
		IBooleanVariable c = BooleanVariable.getBooleanVariable("c");

		/**
		 * Create problems which constrain the values of these Saffron objects:
		 */

		/**
		 * Create the IProblem of satisfying all of these constraining problems:
		 */

		IProblem threeBitAdder1 = new ThreeBitAdder(w, x, y, z, c).and(new BitFixer(w, false))
				.and(new BitFixer(x, true)).and(new BitFixer(y, true));

		/**
		 * Solve the IProblem:
		 */

		IProblemMessage s = threeBitAdder1.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println(w.toBit());
			System.out.println(x.toBit());
			System.out.println(y.toBit());
			System.out.println(z.toBit());
			System.out.println(c.toBit());
		}
		else
			System.out.println("No solution.");

	}
}