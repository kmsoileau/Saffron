package demos.bits.strings;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.BitStringArrayBasiser;
import bits.strings.BitStringFixer;

public class BitStringArrayBasiserDemo
{
	public static void main(String[] args) throws Exception
	{
		/**
		 * Set Java variables:
		 */

		/**
		 * Set globals:
		 */

		/**
		 * Create Saffron objects and arrays:
		 */

		// Collection of IBitStrings to be expressed
		IBitString[] C = new IBitString[]
		{ new BitString("01001010"), new BitString("10101010"), new BitString("00001000"), new BitString("00100000"),
				new BitString("00101000") };

		int cLength = C.length;

		int bLength = 4;

		// Collection of basis IBitStrings
		IBitString[] B = new IBitString[bLength];
		for (int i = 0; i < bLength; i++)
			B[i] = new BitString(C[0].size());

		IBitString[] included = new BitString[cLength];
		for (int i = 0; i < cLength; i++)
		{
			included[i] = new BitString(bLength);
		}

		/**
		 * Create problems which constrain the values of these Saffron objects:
		 */

		IProblem p1 = new BitStringFixer(C);
		IProblem p2 = new BitStringArrayBasiser(C, B, included);

		/**
		 * Create the IProblem of satisfying all of these constraining problems:
		 */

		IProblem prob = new Conjunction(p1, p2);

		/**
		 * Solve the IProblem:
		 */

		IProblemMessage s = prob.findModel(Problem.defaultSolver());

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
