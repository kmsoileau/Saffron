package demos;

import java.util.List;

import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.IBooleanLiteral;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;
import bits.ProblemBitLinker;

public class ProblemBitLinkerDemo
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable b = BooleanVariable.getBooleanVariable("b");

		IProblem p = Problem.unsolvableProblem();

		IProblem testProblem = new ProblemBitLinker(p, b);

		System.out.println(testProblem);

		List<IBooleanLiteral> s = testProblem
				.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("b=" + b.getValue());
		}
		else
			System.out.println("No solution.");
	}
}
