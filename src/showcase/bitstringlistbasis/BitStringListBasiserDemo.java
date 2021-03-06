package showcase.bitstringlistbasis;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.lists.BitStringList;
import bits.strings.lists.BitStringListBasiser;
import bits.strings.lists.BitStringListFixer;
import bits.strings.lists.IBitStringList;

public class BitStringListBasiserDemo
{
	public static void main(String[] args) throws Exception
	{
		// Collection of IBitStrings to be expressed
		IBitStringList C = new BitStringList(new IBitString[]
		{ new BitString("01001010"), new BitString("10101010"), new BitString("00001000"), new BitString("00100000"),
				new BitString("00101000") });

		// Collection of basis IBitStrings
		IBitStringList B = new BitStringList(4);
		for (int i = 0; i < B.size(); i++)
			B.set(i, new BitString(C.getBitString(0).size()));

		IBitStringList included = new BitStringList();
		for (int i = 0; i < C.size(); i++)
		{
			included.add(new BitString(B.size()));
		}

		IProblemMessage s = new Conjunction(new BitStringListFixer(C), new BitStringListBasiser(C, B, included))
				.findModel(Problem.defaultSolver());

		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("C=" + C.toBits());
			System.out.println("B=" + B.toBits());
			for (int i = 0; i < C.size(); i++)
				System.out.println("included[" + i + "]=" + included.getBitString(i).toBits());
		}
		else
			System.out.println("No solution.");
	}
}
