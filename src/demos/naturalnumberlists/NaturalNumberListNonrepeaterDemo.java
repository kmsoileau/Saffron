package demos.naturalnumberlists;

import naturalnumberlists.INaturalNumberList;
import naturalnumberlists.NaturalNumberList;
import naturalnumberlists.NaturalNumberListFixer;
import naturalnumberlists.NaturalNumberListNonrepeater;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblem;
import bits.IProblemMessage;
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
		IProblemMessage s = problem.findModel(Problem.defaultSolver());
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