package demos;

import java.util.List;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IBooleanLiteral;
import bits.IProblem;
import bits.Problem;
import bitstrings.BitString;
import bitstrings.BitStringBitFixer;

public class BitStringBitFixerDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitString b = new BitString(new boolean[20]);
		IProblem p1 = new BitStringBitFixer(b, 3, true);
		IProblem p2 = new BitStringBitFixer(b, 7, true);
		IProblem p = new Conjunction(p1, p2);
		List<IBooleanLiteral> s = p.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println(b);
		}
		else
			System.out.println("No solution.");
	}
}
