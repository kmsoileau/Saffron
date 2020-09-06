package demos.bits.strings.lists;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.lists.BitStringArrayBasiser;
import bits.strings.lists.BitStringList;
import bits.strings.lists.BitStringListFixer;
import bits.strings.lists.IBitStringList;

public class BitStringListBasiserDemo
{
	public static void main(String[] args) throws Exception
	{
		// Collection of IBitStrings to be expressed
		IBitStringList C = new BitStringList(new IBitString[]
		{ new BitString("01001010"), new BitString("10101010"),
				new BitString("00001000"), new BitString("00100000"),
				new BitString("00101000") });

		int bLength = 4;

		int cLength = C.size();

		// Collection of basis IBitStrings
		IBitStringList B = new BitStringList();
		for (int i = 0; i < bLength; i++)
			B.add(new BitString(C.getBitString(0).size()));

		IBitStringList included = new BitStringList();
		for (int i = 0; i < cLength; i++)
		{
			included.add(new BitString(bLength));
		}

		IProblemMessage s = new Conjunction(new BitStringListFixer(C),
				new BitStringArrayBasiser(C, B, included)).findModel(Problem
				.defaultSolver());

		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			for (int i = 0; i < cLength; i++)
				System.out
						.println("C[" + i + "]=" + C.getBitString(i).toBits());
			for (int i = 0; i < bLength; i++)
				System.out
						.println("B[" + i + "]=" + B.getBitString(i).toBits());
			for (int i = 0; i < cLength; i++)
				System.out.println("included[" + i + "]="
						+ included.getBitString(i).toBits());
		}
		else
			System.out.println("No solution.");
	}
}
