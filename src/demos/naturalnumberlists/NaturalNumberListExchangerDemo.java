package demos.naturalnumberlists;

import java.util.List;

import naturalnumberlists.INaturalNumberList;
import naturalnumberlists.NaturalNumberList;
import naturalnumberlists.NaturalNumberListExchanger;
import naturalnumberlists.NaturalNumberListFixer;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.IProblem;
import bits.Problem;

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

		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("s1=" + s1);
			System.out.println("s2=" + s2);
		}
		else
			System.out.println("No solution.");
	}
}
