package demos.bitstrings;

import java.util.List;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IBooleanLiteral;
import bits.Problem;
import bitstrings.BitString;
import bitstrings.BitStringFixer;
import bitstrings.HittingBitStringer;

public class HittingBitStringerDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitString[] C = new IBitString[]
		{ new BitString("01001000"), new BitString("10101010"),
				new BitString("00001000"), new BitString("00100000") };

		IBitString Y = new BitString(C[0].size());

		List<IBooleanLiteral> s = new Conjunction(new BitStringFixer(C),
				new HittingBitStringer(C, Y)).findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			for (int i = 0; i < C.length; i++)
				System.out.println(C[i].toBits());
			System.out.println("\n" + Y.toBits());
		}
		else
			System.out.println("No solution.");
	}
}
