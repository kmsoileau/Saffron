package demos.naturalnumbers;

import java.util.List;

import naturalnumbers.NaturalNumber;
import naturalnumbers.SetPacker;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IBooleanLiteral;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import bitstrings.BitString;
import bitstrings.BitStringFixer;

public class SetPackerDemo
{

	public static void main(String[] args) throws Exception
	{
		IBitString[] C = new IBitString[] { new BitString("01001010"), new
		BitString("10101010"), new BitString("10000000"), new
		BitString("00100000") };

		

		IProblem p1 = Problem.newProblem();
		for (int i = 0; i < C.length; i++)
			p1 = new Conjunction(p1, new BitStringFixer(C[i]));

		int sizeOfPacking = 3;

		IBitString membership = new BitString(C.length);
		INaturalNumber K = new NaturalNumber(sizeOfPacking);

		List<IBooleanLiteral> s = new Conjunction(p1, new BitStringFixer(K),
				new SetPacker(C, K, membership)).findModel(Problem
				.defaultSolver());

		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("C[0]=" + C[0].toBits());
			System.out.println("membership=" + membership.toBits());
			for (int i = 0; i < C.length; i++)
				System.out.println("C[" + i + "]=" + C[i].toBits());
		}
		else
			System.out.println("No solution.");
	}

}
