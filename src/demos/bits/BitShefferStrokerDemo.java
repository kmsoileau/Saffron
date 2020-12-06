package demos.bits;

import java.util.List;

import bits.BitFixer;
import bits.BitShefferStroker;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

public class BitShefferStrokerDemo
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

		// Construct the object that implements the constraint x & y = z :
		IProblem bitShefferStroker1 = new BitShefferStroker(x, y);

		IProblem bfx1 = new BitFixer(x, true);
		IProblem bfy1 = new BitFixer(y, false);

		/**
		 * Create the IProblem of satisfying all of these constraining problems:
		 */

		// Combine the constraints into a Problem object :
		IProblem p1 = new Conjunction(bitShefferStroker1, bfx1, bfy1);
		System.out.println(p1);

		/**
		 * Solve the IProblem:
		 */

		IProblemMessage s = p1.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			List<IBooleanLiteral> v1 = s.getLiterals();
			System.out.println(v1);
		}
		else
			System.out.println("No solution.");
	}
}