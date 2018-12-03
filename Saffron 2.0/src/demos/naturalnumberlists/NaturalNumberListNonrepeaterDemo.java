package demos.naturalnumberlists;

import java.util.List;

import naturalnumberlists.INaturalNumberList;
import naturalnumberlists.NaturalNumberList;
import naturalnumberlists.NaturalNumberListFixer;
import naturalnumberlists.NaturalNumberListNonrepeater;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.IProblem;
import bits.Problem;

public class NaturalNumberListNonrepeaterDemo
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumberList testList = new NaturalNumberList("y", new long[]
		{ 5, 6, 2, 4 });
		IProblem problem = new Conjunction(
				new NaturalNumberListFixer(testList),
				new NaturalNumberListNonrepeater(testList));
		System.out.println(problem);
		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("s2= " + testList);
		}
		else
			System.out.println("No solution.");
	}
}