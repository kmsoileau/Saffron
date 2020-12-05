package demos.bits;

import bits.BitNander;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

public class BitNanderDemo
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

		IProblem bitNander1 = new BitNander(x, y, z);

		/**
		 * Solve the IProblem:
		 */

		System.out.println(bitNander1);
		IProblemMessage s = bitNander1.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(bitNander1.findModel(Problem.defaultSolver()).getLiterals());
			System.out.println("x = " + x.getValue());
			System.out.println("y = " + y.getValue());
			System.out.println("z = " + z.getValue());
		}
		else
			System.out.println("No solution.");
	}
}