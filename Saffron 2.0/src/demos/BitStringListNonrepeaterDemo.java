package demos;

import java.util.List;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.IProblem;
import bits.Problem;
import bitstringlists.BitStringList;
import bitstringlists.BitStringListFixer;
import bitstringlists.BitStringListNonrepeater;
import bitstringlists.IBitStringList;

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
		IProblem problem = new Conjunction(new BitStringListFixer(testList),
				new BitStringListNonrepeater(testList));
		System.out.println(problem);
		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());
		System.out.println(s);
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("s2= " + testList);
		}
		else
			System.out.println("No solution.");
	}
}