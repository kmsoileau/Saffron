package demos.naturalnumberintervals;

import naturalnumberintervals.INaturalNumberInterval;
import naturalnumberintervals.NaturalNumberInterval;
import naturalnumberintervals.NaturalNumberIntervalDisjointer;
import naturalnumbers.NaturalNumberFixer;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblem;
import bits.IProblemMessage;
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
		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE
				&& s.getLiterals().size() > 0)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("L = " + L);
			System.out.println("R = " + R);
		}
		else
			System.out.println("No solution.");
	}
}
