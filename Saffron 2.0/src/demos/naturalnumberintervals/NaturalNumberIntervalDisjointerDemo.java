package demos.naturalnumberintervals;

import java.util.List;

import naturalnumberinterval.INaturalNumberInterval;
import naturalnumberinterval.NaturalNumberInterval;
import naturalnumberinterval.NaturalNumberIntervalDisjointer;
import naturalnumbers.NaturalNumberFixer;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.IProblem;
import bits.Problem;

public class NaturalNumberIntervalDisjointerDemo
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumberInterval L = new NaturalNumberInterval("L");
		INaturalNumberInterval R = new NaturalNumberInterval("R");

		IProblem[] p = new IProblem[]
		{ new NaturalNumberFixer(L.getLower(), 3),
				new NaturalNumberFixer(L.getUpper(), 4),
				new NaturalNumberFixer(R.getLower(), 2),
				new NaturalNumberFixer(R.getUpper(), 7),
				new NaturalNumberIntervalDisjointer(L, R) };

		IProblem problem = new Conjunction(p);

		System.out.println(problem);
		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("L = " + L);
			System.out.println("R = " + R);
		}
		else
			System.out.println("No solution.");
	}
}
