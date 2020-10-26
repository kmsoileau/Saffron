package demos.bits.bugs;

import bits.BitFixer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import in_development.AlternativeDenial;

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

		IProblem bf1 = new BitFixer(x1, true);
		IProblem bf2=new BitFixer(x1, false);
		IProblem bf3=new BitFixer(x2, true);
		IProblem bf4=new BitFixer(x2, false);
		IProblem[] ary = new IProblem[]
				{ bf1, bf2, bf3, bf4 };
		IProblem problem = new AlternativeDenial(ary);

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