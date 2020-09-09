package demos.naturalnumbers.intervals;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.intervals.INaturalNumberInterval;
import naturalnumbers.intervals.NaturalNumberInterval;
import naturalnumbers.intervals.NaturalNumberIntervalContainer;
import naturalnumbers.intervals.NaturalNumberIntervalFixer;

public class NaturalNumberIntervalContainerDemo1
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumberInterval L = new NaturalNumberInterval("L");
		INaturalNumberInterval R = new NaturalNumberInterval("R");

		IProblem[] p = new IProblem[]
		{ new NaturalNumberIntervalFixer(L, 3, 9), new NaturalNumberIntervalFixer(R, 2, 11),
				new NaturalNumberIntervalContainer(L, R) };

		IProblem problem = new Conjunction(p);

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
