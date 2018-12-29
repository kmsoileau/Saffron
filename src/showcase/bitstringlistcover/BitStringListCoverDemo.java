package showcase.bitstringlistcover;

import java.util.List;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IBooleanLiteral;
import bits.Problem;
import bitstringlists.BitStringList;
import bitstringlists.BitStringListFixer;
import bitstringlists.IBitStringList;
import bitstrings.BitString;
import bitstrings.BitStringCoverer;

public class BitStringListCoverDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitStringList C = new BitStringList(new IBitString[]
		{ new BitString("01001010"), new BitString("10111010"),
				new BitString("00001000"), new BitString("00100100"),
				new BitString("00101001") });

		int cLength = C.size();

		IBitString includedInCover = new BitString(cLength);

		List<IBooleanLiteral> s = new Conjunction(new BitStringListFixer(C),
				new BitStringCoverer(C, includedInCover)).findModel(Problem
				.defaultSolver());

		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			for (int i = 0; i < cLength; i++)
				if (includedInCover.getBooleanVariable(i).getValue())
					System.out.println("C[" + i + "]="
							+ C.getBitString(i).toBits());
		}
		else
			System.out.println("No solution.");
	}
}
