package demos.naturalnumberintervals;

import java.util.List;

import naturalnumberinterval.INaturalNumberInterval;
import naturalnumberinterval.NaturalNumberInterval;
import naturalnumberinterval.NaturalNumberIntervalSizer;
import bits.BooleanLiteral;
import bits.IBooleanLiteral;
import bits.IProblem;
import bits.Problem;

public class NaturalNumberIntervalSizerDemo
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumberInterval L = new NaturalNumberInterval("L");

		IProblem problem = new NaturalNumberIntervalSizer(L, 1);

		System.out.println(problem);
		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("L = " + L);
		}
		else
			System.out.println("No solution.");
	}
}
