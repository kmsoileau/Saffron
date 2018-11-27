package demos;

import java.util.List;

import naturalnumberinterval.INaturalNumberInterval;
import naturalnumberinterval.NaturalNumberInterval;
import naturalnumberinterval.NaturalNumberIntervalFixer;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.IProblem;
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
				new naturalnumberinterval.NaturalNumberIntervalIntersector(AB,
						CD) };

		IProblem problem = new Conjunction(p);

		System.out.println(problem);
		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("[A,B] = " + AB);
			System.out.println("[C,D] = " + CD);
		}
		else
			System.out.println("No solution.");
	}
}
