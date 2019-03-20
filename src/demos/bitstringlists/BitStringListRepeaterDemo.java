package demos.bitstringlists;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblem;
import bits.IProblemMessage;
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