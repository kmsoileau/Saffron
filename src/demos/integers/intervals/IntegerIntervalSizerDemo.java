package demos.integers.intervals;

import bits.BooleanLiteral;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import integers.intervals.IIntegerInterval;
import integers.intervals.IntegerInterval;
import integers.intervals.IntegerIntervalSizer;

public class IntegerIntervalSizerDemo
{
	public static void main(String[] args) throws Exception
	{
		IIntegerInterval L = new IntegerInterval("L");

		IProblem problem = new IntegerIntervalSizer(L, 1);

		System.out.println(problem);
		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("L = " + L);
		}
		else
			System.out.println("No solution.");
	}
}
