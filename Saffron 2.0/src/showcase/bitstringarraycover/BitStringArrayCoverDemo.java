package showcase.bitstringarraycover;

import java.util.List;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IBooleanLiteral;
import bits.Problem;
import bitstringlists.BitStringCover;
import bitstrings.BitString;
import bitstrings.BitStringFixer;

public class BitStringArrayCoverDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitString[] C = new IBitString[]
		{ new BitString("01001010"), new BitString("10111010"),
				new BitString("00001000"), new BitString("00100100"),
				new BitString("00101001") };

		int cLength = C.length;

		IBitString includedInCover = new BitString(cLength);

		List<IBooleanLiteral> s = new Conjunction(new BitStringFixer(C),
				new BitStringCover(C, includedInCover)).findModel(Problem
				.defaultSolver());

		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			for (int i = 0; i < cLength; i++)
				if (includedInCover.getBooleanVariable(i).getValue())
					System.out.println("C[" + i + "]=" + C[i].toBits());
		}
		else
			System.out.println("No solution.");
	}
}
