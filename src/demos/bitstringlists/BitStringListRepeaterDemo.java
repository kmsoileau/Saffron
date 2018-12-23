package demos.bitstringlists;

import java.util.List;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.IProblem;
import bits.Problem;
import bitstringlists.BitStringList;
import bitstringlists.BitStringListFixer;
import bitstringlists.BitStringListRepeater;
import bitstringlists.IBitStringList;

public class BitStringListRepeaterDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitStringList testList = new BitStringList("y", new boolean[][]
		{
		{ true, false, false },
		{ false, true, false },
		{ false, true, false },
		{ true, false, false } });
		IProblem problem = new Conjunction(new BitStringListFixer(testList),
				new BitStringListRepeater(testList));
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