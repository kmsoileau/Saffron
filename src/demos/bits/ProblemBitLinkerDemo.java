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
		IBooleanVariable b = BooleanVariable.getBooleanVariable("b");

		// IProblem p = EnhancedProblem.unsolvableProblem();
		IProblem p = EnhancedProblem.trivialProblem();

		IProblem testProblem = new ProblemBitLinker(p, b);

		System.out.println(testProblem);

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
