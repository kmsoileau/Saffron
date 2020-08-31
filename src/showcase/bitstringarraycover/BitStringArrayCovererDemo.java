package showcase.bitstringarraycover;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.BitStringArrayCoverer;
import bits.strings.BitStringFixer;

public class BitStringArrayCovererDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitString[] C = new IBitString[]
		{ new BitString("01001010"), new BitString("10111010"),
				new BitString("00001000"), new BitString("00100100"),
				new BitString("00101001") };

		int cLength = C.length;

		IBitString includedInCover = new BitString(cLength);

		IProblemMessage s = new Conjunction(new BitStringFixer(C),
				new BitStringArrayCoverer(C, includedInCover))
				.findModel(Problem.defaultSolver());

		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			for (int i = 0; i < cLength; i++)
				if (includedInCover.getBooleanVariable(i).getValue())
					System.out.println("C[" + i + "]=" + C[i].toBits());
		}
		else
			System.out.println("No solution.");
	}
}
