package demos.naturalnumbers;

import java.util.List;

import naturalnumbers.MinimumCover;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IBooleanLiteral;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import bitstringlists.BitStringList;
import bitstringlists.BitStringListFixer;
import bitstringlists.IBitStringList;
import bitstrings.BitString;

public class 1`
{
	public static void main(String[] args) throws Exception
	{
		long maxSizeOfCover = 5L;

		INaturalNumber K = new NaturalNumber(maxSizeOfCover);

		IBitStringList C = new BitStringList(new IBitString[]
		{ new BitString("10000000"), new BitString("01111100"),
				new BitString("00001000"), new BitString("00000011") });

		IBitString included = new BitString(C.size());

		IProblem problem = new Conjunction(new BitStringListFixer(C),
				new NaturalNumberFixer(K), new MinimumCover(C, included, K));

		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			for (int i = 0; i < included.size(); i++)
				if (included.getBooleanVariable(i).getValue())
					System.out.println(C.getBitString(i).toBits());
		}
		else
			System.out.println("No solution.");
	}
}
