package demos.naturalnumberintervals;

import naturalnumberintervals.INaturalNumberInterval;
import naturalnumberintervals.NaturalNumberInterval;
import naturalnumberintervals.NaturalNumberIntervalFixer;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

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
				new naturalnumberintervals.NaturalNumberIntervalIntersector(AB,
						CD) };

		IProblem problem = new Conjunction(p);

		System.out.println(problem);
		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE
				&& s.getLiterals().size() > 0)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("[A,B] = " + AB);
			System.out.println("[C,D] = " + CD);
		}
		else
			System.out.println("No solution.");
	}
}
