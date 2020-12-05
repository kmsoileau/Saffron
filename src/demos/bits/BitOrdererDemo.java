package demos.bits;

import bits.BitFixer;
import bits.BitOrderer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

public class BitOrdererDemo
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

		// Create the BooleanVariables:
		IBooleanVariable x = BooleanVariable.getBooleanVariable("x");
		IBooleanVariable y = BooleanVariable.getBooleanVariable("y");

		/**
		 * Create problems which constrain the values of these Saffron objects:
		 */

		// Construct the object that implements the constraint x <= y :
		IProblem bitOrderer1 = new BitOrderer(x, y);

		// Constrain the values of x and y :
		IProblem bfx1 = new BitFixer(x, false);
		IProblem bfy1 = new BitFixer(y, false);

		/**
		 * Create the IProblem of satisfying all of these constraining problems:
		 */

		// Combine the constraints into a Problem object :
		IProblem p1 = new Conjunction(bitOrderer1, bfx1, bfy1);

		/**
		 * Solve the IProblem:
		 */

		System.out.println(p1);
		// Find a solution to the Problem object :
		IProblemMessage s = p1.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("x = " + x.getValue());
			System.out.println("y = " + y.getValue());
		}
		else
			System.out.println("No solution.");
	}
}