package demos.bits;

import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.ProblemBitLinker;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;

public class ProblemBitLinkerDemo3
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
		INaturalNumber N = new NaturalNumber();

		/**
		 * Create problems which constrain the values of these Saffron objects:
		 */

		IProblem p = new Disjunction(new NaturalNumberFixer(N, 3), new NaturalNumberFixer(N, 13));

		/**
		 * Create the IProblem of satisfying all of these constraining problems:
		 */

		IProblem testProblem = new Conjunction(new ProblemBitLinker(p, b), new NaturalNumberFixer(N, 13));

		/**
		 * Solve the IProblem:
		 */

		System.out.println(testProblem);

		IProblemMessage s = testProblem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("b=" + b.getValue());
			System.out.println("N=" + N);
		}
		else
			System.out.println("No solution.");
	}
}
