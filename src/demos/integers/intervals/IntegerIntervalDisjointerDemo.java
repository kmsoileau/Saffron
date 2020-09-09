package demos.integers.intervals;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import integers.IntegerFixer;
import integers.intervals.IIntegerInterval;
import integers.intervals.IntegerInterval;
import integers.intervals.IntegerIntervalDisjointer;

public class IntegerIntervalDisjointerDemo
{
	public static void main(String[] args) throws Exception
	{
		IIntegerInterval L = new IntegerInterval("L");
		IIntegerInterval R = new IntegerInterval("R");

		IProblem[] p = new IProblem[]
		{ new IntegerFixer(L.getLower(), 0), new IntegerFixer(L.getUpper(), 0), new IntegerFixer(R.getLower(), 1),
				new IntegerFixer(R.getUpper(), 1), new IntegerIntervalDisjointer(L, R) };

		IProblem problem = new Conjunction(p);

		System.out.println(problem);
		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("L = " + L);
			System.out.println("R = " + R);
		}
		else
			System.out.println("No solution.");
	}
}
