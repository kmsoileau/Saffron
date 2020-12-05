package demos.bits;

import bits.BitNoter;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

public class BitNoterDemo
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

		/**
		 * Create the IProblem of satisfying all of these constraining problems:
		 */

		IProblem bitNoter = new BitNoter(x, y);

		/**
		 * Solve the IProblem:
		 */

		System.out.println(bitNoter);
		IProblemMessage s = bitNoter.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(bitNoter.findModel(Problem.defaultSolver()).getLiterals());
			System.out.println("x = " + x.getValue());
			System.out.println("y = " + y.getValue());
		}
		else
			System.out.println("No solution.");
	}
}