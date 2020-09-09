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
		IBooleanVariable bvX = BooleanVariable.getBooleanVariable("X");

		IProblem problem = new BitFixer(bvX, true);

		System.out.println(problem);

		IProblemMessage solutionSat4j = problem.findModel(Problem.defaultSolver());

		System.out.println("Sat4j Solution: " + solutionSat4j);

		BooleanLiteral.interpret(solutionSat4j.getLiterals());

		System.out.println("Saffron Solution for X: " + bvX.getValue());
	}
}