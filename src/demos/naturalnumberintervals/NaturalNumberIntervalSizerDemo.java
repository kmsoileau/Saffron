package demos.naturalnumberintervals;

import naturalnumberintervals.INaturalNumberInterval;
import naturalnumberintervals.NaturalNumberInterval;
import naturalnumberintervals.NaturalNumberIntervalSizer;
import bits.BooleanLiteral;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

public class NaturalNumberIntervalSizerDemo
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumberInterval L = new NaturalNumberInterval("L");

		IProblem problem = new NaturalNumberIntervalSizer(L, 1);

		System.out.println(problem);
		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE
				&& s.getLiterals().size() > 0)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("L = " + L);
		}
		else
			System.out.println("No solution.");
	}
}
