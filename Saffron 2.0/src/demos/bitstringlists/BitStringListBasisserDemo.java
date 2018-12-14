package demos.bitstringlists;

import java.util.List;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IBooleanLiteral;
import bits.Problem;
import bitstringlists.BitStringList;
import bitstringlists.BitStringListBasisser;
import bitstringlists.BitStringListFixer;
import bitstringlists.IBitStringList;
import bitstrings.BitString;

public class BitStringListBasisserDemo
{
	public static void main(String[] args) throws Exception
	{
		// Collection of IBitStrings to be expressed
		IBitStringList C = new BitStringList(new IBitString[]
		{ new BitString("01001010"), new BitString("10101010"),
				new BitString("00001000"), new BitString("00100000"),
				new BitString("00101000") });

		// Collection of basis IBitStrings
		IBitStringList B = new BitStringList(4);
		for (int i = 0; i < B.size(); i++)
			B.set(i, new BitString(C.getBitString(0).size()));

		IBitString[] included = new IBitString[C.size()];
		for (int i = 0; i < C.size(); i++)
		{
			included[i] = new BitString(B.size());
		}

		List<IBooleanLiteral> s = new Conjunction(new BitStringListFixer(C),
				new BitStringListBasisser(C, B, included)).findModel(Problem
				.defaultSolver());

		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("C=" + C.toBits());
			System.out.println("B=" + B.toBits());
			for (int i = 0; i < C.size(); i++)
				System.out.println("included[" + i + "]="
						+ included[i].toBits());
		}
		else
			System.out.println("No solution.");
	}
}
