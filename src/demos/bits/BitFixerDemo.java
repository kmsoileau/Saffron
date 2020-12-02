package demos.bits;

import bits.BitFixer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

/**
 * 
 *************************************** <p>
 * IProblem-0
 * 
 *************************************** <p>
 * { X }
 * 
 *************************************** <p>
 * 1 clauses generated.
 * 
 *************************************** <p>
 * Sat4j Solution: [ X ]
 * <p>
 * Saffron Solution for X: true
 * 
 */
public class BitFixerDemo
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

		IBooleanVariable bvX = BooleanVariable.getBooleanVariable("X");

		/**
		 * Create problems which constrain the values of these Saffron objects:
		 */

		/**
		 * Create the IProblem of satisfying all of these constraining problems:
		 */

		IProblem problem = new BitFixer(bvX, true);

		/**
		 * Solve the IProblem:
		 */

		IProblemMessage solutionSat4j = problem.findModel(Problem.defaultSolver());
		if (solutionSat4j.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(solutionSat4j.getLiterals());
			System.out.println("Sat4j Solution: " + solutionSat4j);
			System.out.println("Saffron Solution for X: " + bvX.getValue());
		}
		else
			System.out.println("No solution.");
	}
}