package demos.bits;

import in_development.ProblemBitLinker;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

public class ProblemBitLinkerDemo
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable b = BooleanVariable.getBooleanVariable("b");

		// IProblem p = Problem.unsolvableProblem();
		IProblem p = Problem.trivialProblem();

		IProblem testProblem = new ProblemBitLinker(p, b);

		System.out.println(testProblem);

		IProblemMessage s = testProblem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE
				&& s.getLiterals().size() > 0)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("b=" + b.getValue());
		}
		else
			System.out.println("No solution.");
	}
}
