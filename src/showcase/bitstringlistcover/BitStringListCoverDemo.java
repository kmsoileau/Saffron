package showcase.bitstringlistcover;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.BitStringArrayCoverer;
import bits.strings.lists.BitStringList;
import bits.strings.lists.BitStringListFixer;
import bits.strings.lists.IBitStringList;

public class BitStringListCoverDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitStringList C = new BitStringList(new IBitString[]
		{ new BitString("01001010"), new BitString("10111010"), new BitString("00001000"), new BitString("00100100"),
				new BitString("00101001") });

		int cLength = C.size();

		IBitString includedInCover = new BitString(cLength);

		IProblemMessage s = new Conjunction(new BitStringListFixer(C), new BitStringArrayCoverer(C, includedInCover))
				.findModel(Problem.defaultSolver());

		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			for (int i = 0; i < cLength; i++)
				if (includedInCover.getBooleanVariable(i).getValue())
					System.out.println("C[" + i + "]=" + C.getBitString(i).toBits());
		}
		else
			System.out.println("No solution.");
	}
}
