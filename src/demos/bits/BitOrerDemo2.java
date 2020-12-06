package demos.bits;

import bits.BitOrer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

public class BitOrerDemo2
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
		IBooleanVariable z = BooleanVariable.getBooleanVariable("z");

		/**
		 * Create problems which constrain the values of these Saffron objects:
		 */

		/**
		 * Create the IProblem of satisfying all of these constraining problems:
		 */

		IProblem bitOrer1 = new BitOrer(x, y, z);

		/**
		 * Solve the IProblem:
		 */

		IProblemMessage s = bitOrer1.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(bitOrer1.findModel(Problem.defaultSolver()).getLiterals());
			System.out.println("x = " + x.getValue());
			System.out.println("y = " + y.getValue());
			System.out.println("z = " + z.getValue());
		}
		else
			System.out.println("No solution.");
	}
}