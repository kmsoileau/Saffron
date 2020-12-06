package demos.bits;

import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.EnhancedProblem;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.ProblemBitLinker;

public class ProblemBitLinkerDemo
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

		IBooleanVariable b = BooleanVariable.getBooleanVariable("b");

		/**
		 * Create problems which constrain the values of these Saffron objects:
		 */

		IProblem p = EnhancedProblem.trivialProblem();

		/**
		 * Create the IProblem of satisfying all of these constraining problems:
		 */

		IProblem testProblem = new ProblemBitLinker(p, b);

		/**
		 * Solve the IProblem:
		 */

		IProblemMessage s = testProblem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("b=" + b.getValue());
		}
		else
			System.out.println("No solution.");
	}
}
