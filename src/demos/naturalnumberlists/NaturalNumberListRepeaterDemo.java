package demos.naturalnumberlists;

import naturalnumberlists.INaturalNumberList;
import naturalnumberlists.NaturalNumberList;
import naturalnumberlists.NaturalNumberListFixer;
import naturalnumberlists.NaturalNumberListRepeater;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblem;
import bits.IProblemMessage;

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
		if (s.getStatus() == IProblemMessage.SATISFIABLE
				&& s.getLiterals().size() > 0)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("s2= " + testList);
		}
		else
			System.out.println("No solution.");
	}
}