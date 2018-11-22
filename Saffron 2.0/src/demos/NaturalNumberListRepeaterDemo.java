package demos;

import java.util.List;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.IProblem;
import naturalnumberlists.INaturalNumberList;
import naturalnumberlists.NaturalNumberList;
import naturalnumberlists.NaturalNumberListFixer;
import naturalnumberlists.NaturalNumberListRepeater;

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
		List<IBooleanLiteral> s = problem.findModel();
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("s2= " + testList);
		}
		else
			System.out.println("No solution.");
	}
}