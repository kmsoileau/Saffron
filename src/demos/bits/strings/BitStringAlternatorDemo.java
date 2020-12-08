package demos.bits.strings;

import bits.BitFixer;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.BitStringAlternator;

public class BitStringAlternatorDemo
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

		IBitString white = new BitString(new boolean[20]);

		/**
		 * Create problems which constrain the values of these Saffron objects:
		 */

		IProblem bsa = new BitStringAlternator(white);
		IProblem fixBit = new BitFixer(white.getBooleanVariable(2), false);

		/**
		 * Create the IProblem of satisfying all of these constraining problems:
		 */

		IProblem problem = new Conjunction(bsa, fixBit);
		System.out.println(problem);

		/**
		 * Solve the IProblem:
		 */

		IProblemMessage s = problem.findModel(Problem.defaultSolver());

		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println(white.toBits());
		}
		else
			System.out.println("No solution.");
	}
}
