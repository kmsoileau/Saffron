package demos.naturalnumbers.lists;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.NaturalNumber;
import naturalnumbers.lists.INaturalNumberList;
import naturalnumbers.lists.NaturalNumberList;
import naturalnumbers.lists.NaturalNumberListFixer;
import naturalnumbers.lists.NaturalNumberListJoiner;

public class NaturalNumberListJoinerDemo
{
	public static void main(String[] args) throws Exception
	{
		NaturalNumber.setLargestNaturalNumber(212);

		INaturalNumberList a = new NaturalNumberList("A", new long[]
		{ 137, 156, });
		INaturalNumberList b = new NaturalNumberList("B", new long[]
		{ 212 });
		INaturalNumberList target = new NaturalNumberList("target", new long[3]);

		IProblem aFix = new NaturalNumberListFixer(a);
		IProblem bFix = new NaturalNumberListFixer(b);
		IProblem join = new NaturalNumberListJoiner(target, a, b);

		IProblem problem = new Conjunction(aFix, bFix, join);

		System.out.println(problem);

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("slm= " + a);
			System.out.println("bsl= " + b);
			System.out.println("target= " + target);
		}
		else
			System.out.println("No solution.");
	}
}
