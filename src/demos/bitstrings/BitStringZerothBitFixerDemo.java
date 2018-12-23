package demos.bitstrings;

import java.util.List;

import bits.BooleanLiteral;
import bits.IBitString;
import bits.IBooleanLiteral;
import bits.IProblem;
import bits.Problem;
import bitstrings.BitString;
import bitstrings.BitStringZerothBitFixer;

public class BitStringZerothBitFixerDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitString b = new BitString(new boolean[20]);
		IProblem problem = new BitStringZerothBitFixer(b, false);

		System.out.println(problem);
		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());

		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println(b);
		}
		else
			System.out.println("No solution.");
	}
}