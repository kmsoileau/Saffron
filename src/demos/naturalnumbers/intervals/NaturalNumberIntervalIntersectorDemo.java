package demos.naturalnumbers.intervals;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.intervals.INaturalNumberInterval;
import naturalnumbers.intervals.NaturalNumberInterval;
import naturalnumbers.intervals.NaturalNumberIntervalIntersector;

public class NaturalNumberIntervalIntersectorDemo
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumberInterval L = new NaturalNumberInterval("L");
		INaturalNumberInterval R = new NaturalNumberInterval("R");

		IProblem[] p = new IProblem[]
		{ new NaturalNumberFixer(L.getLower(), 3),
				new NaturalNumberFixer(L.getUpper(), 8),
				new NaturalNumberFixer(R.getLower(), 7),
				new NaturalNumberFixer(R.getUpper(), 9),
				new NaturalNumberIntervalIntersector(L, R) };

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
