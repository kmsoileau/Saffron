package demos.bits.strings.lists;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.lists.BitStringList;
import bits.strings.lists.BitStringListFixer;
import bits.strings.lists.BitStringListNonrepeater;
import bits.strings.lists.IBitStringList;

public class BitStringListNonrepeaterDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitStringList testList = new BitStringList("y", new boolean[][]
		{
				{ true, false, true },
				{ false, true, false, true },
				{ false, true, false },
				{ true, false, false } });
		IProblem problem = new Conjunction(new BitStringListFixer(testList), new BitStringListNonrepeater(testList));
		System.out.println(problem);
		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		System.out.println(s);
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("s2= " + testList);
		}
		else
			System.out.println("No solution.");
	}
}