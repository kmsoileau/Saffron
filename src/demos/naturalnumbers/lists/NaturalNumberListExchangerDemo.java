package demos.naturalnumbers.lists;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.lists.INaturalNumberList;
import naturalnumbers.lists.NaturalNumberList;
import naturalnumbers.lists.NaturalNumberListExchanger;
import naturalnumbers.lists.NaturalNumberListFixer;

public class NaturalNumberListExchangerDemo
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumberList s1 = new NaturalNumberList("x", new long[]
		{ 2, 11, 3, 4, 5, 1, 13, 8 });

		INaturalNumberList s2 = new NaturalNumberList("y", new long[s1.size()]);

		IProblem f1 = new NaturalNumberListFixer(s1);

		IProblem eq = new NaturalNumberListExchanger(s1, s2, 4, 1);

		IProblem problem = new Conjunction(f1, eq);
		System.out.println(problem);

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("s1=" + s1);
			System.out.println("s2=" + s2);
		}
		else
			System.out.println("No solution.");
	}
}
