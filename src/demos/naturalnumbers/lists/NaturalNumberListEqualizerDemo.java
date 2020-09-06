package demos.naturalnumbers.lists;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.lists.INaturalNumberList;
import naturalnumbers.lists.NaturalNumberList;
import naturalnumbers.lists.NaturalNumberListEqualizer;
import naturalnumbers.lists.NaturalNumberListFixer;

public class NaturalNumberListEqualizerDemo
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumberList s1 = new NaturalNumberList("x", new long[]
		{ 2, 11, 3, 4, 5, 1, 13, 8 });
		System.out.println("s1.getName() = " + s1.getName());

		for (int i = 0; i < s1.size(); i++)
			System.out.println("s1.getNaturalNumber(" + i + ") = "
					+ s1.getNaturalNumber(i));

		INaturalNumberList s2 = new NaturalNumberList("y", new long[]
		{ 1, 2, 13, 3, 4, 5, 8, 11 });

		for (int i = 0; i < s2.size(); i++)
			System.out.println("s2.getNaturalNumber(" + i + ") = "
					+ s2.getNaturalNumber(i));

		IProblem f1 = new NaturalNumberListFixer(s1);
		IProblem f2 = new NaturalNumberListFixer(s2);

		IProblem eq = new NaturalNumberListEqualizer(s1, s2);

		IProblem problem = new Conjunction(f1, f2, eq);
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
