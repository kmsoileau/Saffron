package demos.integers.intervals;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import integers.intervals.IIntegerInterval;
import integers.intervals.IntegerInterval;

public class IntegerIntervalDemo
{
	public static void main(String[] args) throws Exception
	{
		IIntegerInterval AB = new IntegerInterval("A");
		IIntegerInterval CD = new IntegerInterval("B");

		IProblem[] p = new IProblem[]
		{ new IntegerIntervalFixer(AB, 3, 4), new IntegerIntervalFixer(CD, 2, 7),
				new integers.intervals.IntegerIntervalIntersector(AB, CD) };

		IProblem problem = new Conjunction(p);

		System.out.println(problem);
		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("[A,B] = " + AB);
			System.out.println("[C,D] = " + CD);
		}
		else
			System.out.println("No solution.");
	}
}
