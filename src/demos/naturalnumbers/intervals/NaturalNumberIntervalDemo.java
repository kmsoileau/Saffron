package demos.naturalnumbers.intervals;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.intervals.INaturalNumberInterval;
import naturalnumbers.intervals.NaturalNumberInterval;
import naturalnumbers.intervals.NaturalNumberIntervalFixer;

public class NaturalNumberIntervalDemo
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumberInterval AB = new NaturalNumberInterval("A");
		INaturalNumberInterval CD = new NaturalNumberInterval("B");

		IProblem[] p = new IProblem[]
		{
				new NaturalNumberIntervalFixer(AB, 3, 4),
				new NaturalNumberIntervalFixer(CD, 2, 7),
				new naturalnumbers.intervals.NaturalNumberIntervalIntersector(AB,
						CD) };

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
