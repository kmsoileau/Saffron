package demos.bits.strings;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.BitStringFixer;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.minimumcover.MinimumSizedCoverer;

public class MinimumCoverDemo
{
	public static void main(String[] args) throws Exception
	{
		long maxSizeOfCover = 5L;

		INaturalNumber K = new NaturalNumber(maxSizeOfCover);

		IBitString[] C = new IBitString[]
		{ new BitString("10000000"), new BitString("01111100"),
				new BitString("00001000"), new BitString("00000011"),
				new BitString("10110101") };

		IBitString included = new BitString(C.length);

		IProblem problem = new Conjunction(new BitStringFixer(C),
				new NaturalNumberFixer(K), new MinimumSizedCoverer(C, included,
						K));

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			for (int i = 0; i < included.size(); i++)
				if (included.getBooleanVariable(i).getValue())
					System.out.println(C[i].toBits());
		}
		else
			System.out.println("No solution.");
	}
}
