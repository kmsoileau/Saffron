package tbs;

import java.util.List;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IBooleanLiteral;
import bits.IProblem;
import bits.Problem;
import bitstringlists.CommonSuperBitString;
import bitstrings.BitString;
import bitstrings.BitStringFixer;

public class CommonSuperBitStringDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitString[] C = new IBitString[]
		{ new BitString("01001010"), new BitString("10101010"),
				new BitString("00001000"), new BitString("00100000"),
				new BitString("00101000") };

		IBitString superSequence = new BitString(22);

		IProblem problem = new Conjunction(new BitStringFixer(C),
				new CommonSuperBitString(C, superSequence));

		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("superSequence= " + superSequence.toBits());
		}
		else
			System.out.println("No solution.");
	}
}
