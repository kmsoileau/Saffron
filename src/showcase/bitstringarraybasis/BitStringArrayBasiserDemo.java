package showcase.bitstringarraybasis;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.BitStringArrayBasiser;
import bits.strings.BitStringFixer;

public class BitStringArrayBasiserDemo
{
	public static void main(String[] args) throws Exception
	{
		// Collection of IBitStrings to be expressed
		IBitString[] C = new IBitString[]
		{ new BitString("01001010"), new BitString("10101010"), new BitString("00001000"), new BitString("00100000"),
				new BitString("00101000") };

		int basisSize = 4;

		int cLength = C.length;

		// Collection of basis IBitStrings
		IBitString[] B = new IBitString[basisSize];
		for (int i = 0; i < B.length; i++)
			B[i] = new BitString(C[0].size());

		int bLength = B.length;

		IBitString[] included = new IBitString[cLength];
		for (int i = 0; i < cLength; i++)
		{
			included[i] = new BitString(bLength);
		}

		IProblemMessage s = new Conjunction(new BitStringFixer(C), new BitStringArrayBasiser(C, B, included))
				.findModel(Problem.defaultSolver());

		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			for (int i = 0; i < cLength; i++)
				System.out.println("C[" + i + "]=" + C[i].toBits());
			for (int i = 0; i < bLength; i++)
				System.out.println("B[" + i + "]=" + B[i].toBits());
			for (int i = 0; i < cLength; i++)
				System.out.println("included[" + i + "]=" + included[i].toBits());
		}
		else
			System.out.println("No solution.");
	}
}
