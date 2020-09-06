package demos.naturalnumbers.lists;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblem;
import bits.IProblemMessage;
import naturalnumbers.lists.INaturalNumberList;
import naturalnumbers.lists.NaturalNumberList;
import naturalnumbers.lists.NaturalNumberListFixer;
import naturalnumbers.lists.NaturalNumberListRepeater;

public class NaturalNumberListRepeaterDemo
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumberList testList = new NaturalNumberList("y", new long[]
		{ 7, 5, 2, 4 });
		IProblem problem = new Conjunction(
				new NaturalNumberListFixer(testList),
				new NaturalNumberListRepeater(testList));
		System.out.println(problem);
		IProblemMessage s = problem.findModel();
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("s2= " + testList);
		}
		else
			System.out.println("No solution.");
	}
}