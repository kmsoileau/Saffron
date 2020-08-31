package demos.bits;

import in_development.AlternativeDenial;
import bits.BitFixer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

public class AlternativeDenialDemo
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

		IBooleanVariable x1 = BooleanVariable.getBooleanVariable("x1");
		IBooleanVariable x2 = BooleanVariable.getBooleanVariable("x2");

		/**
		 * Create problems which constrain the values of these Saffron objects:
		 */

		/**
		 * Create the IProblem of satisfying all of these constraining problems:
		 */

		IProblem problem = new AlternativeDenial(new IProblem[]
		{ new BitFixer(x1, true), new BitFixer(x1, false),
				new BitFixer(x2, true), new BitFixer(x2, false) });

		/**
		 * Solve the IProblem:
		 */

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println(x1);
			System.out.println(x2);
		}
		else
			System.out.println("There is no solution.");
	}
}