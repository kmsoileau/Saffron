package demos.bitstringlists;

import java.util.List;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IBooleanLiteral;
import bits.IProblem;
import bits.Problem;
import bitstringlists.BitStringList;
import bitstringlists.BitStringListFixer;
import bitstringlists.CommonSuperBitString;
import bitstringlists.IBitStringList;
import bitstrings.BitString;

/*
 * Given a collection C of IBitStrings each of size n, and an IBitString 
 * targetBitString of size K>=n, does there exist an IBitString of length 
 * K such that every member of C is a substring of targetBitString?
 */
public class CommonSupersequencerDemo extends Problem implements IProblem
{
	public static void main(String[] args) throws Exception
	{
		int K = 22;

		IBitStringList X = new BitStringList(new IBitString[]
		{ new BitString("01001010"), new BitString("10101010"),
				new BitString("00001000"), new BitString("00100000"),
				new BitString("00101000") });
		IBitString Y = new BitString(K);

		List<IBooleanLiteral> s = new Conjunction(new BitStringListFixer(X),
				new CommonSuperBitString(X, Y)).findModel(Problem
				.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("X= " + X.toBits());
			System.out.println("Y= " + Y.toBits());
		}
		else
			System.out.println("No solution.");
	}
}
