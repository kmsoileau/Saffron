package demos;

import java.util.List;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblem;
import bits.Problem;
import bitstringlists.BitStringList;
import bitstringlists.BitStringListFixer;
import bitstringlists.BitStringListJoiner;
import bitstringlists.IBitStringList;

public class BitStringListJoinerDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitStringList a = new BitStringList("A", new boolean[][]
		{
		{ true, false, false },
		{ true, true, false }, });
		IProblem aFix = new BitStringListFixer(a);

		IBitStringList b = new BitStringList("B", new boolean[][]
		{
		{ true, false, true }, });
		IProblem bFix = new BitStringListFixer(b);

		IBitStringList target = new BitStringList("target", new boolean[3][3]);

		IProblem problem = new Conjunction(aFix, bFix, new BitStringListJoiner(
				target, a, b));

		System.out.println(problem);

		List<?> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("slm= " + a);
			System.out.println("bsl= " + b);
			System.out.println("target= " + target);
		}
		else
			System.out.println("No solution.");
	}

}
