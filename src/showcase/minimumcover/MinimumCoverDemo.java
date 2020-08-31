package showcase.minimumcover;

import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.minimumcover.MinimumSizedCoverer;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.lists.BitStringList;
import bits.strings.lists.BitStringListFixer;
import bits.strings.lists.IBitStringList;

public class MinimumCoverDemo
{
	public static void main(String[] args) throws Exception
	{
		long maxSizeOfCover = 5L;

		INaturalNumber K = new NaturalNumber(maxSizeOfCover);

		IBitStringList C = new BitStringList(new IBitString[]
		{ new BitString("10000000"), new BitString("01111100"),
				new BitString("00001000"), new BitString("00000011"),
				new BitString("10110101") });

		IBitString included = new BitString(C.size());

		IProblem problem = new Conjunction(new BitStringListFixer(C),
				new NaturalNumberFixer(K), new MinimumSizedCoverer(C, included,
						K));

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			for (int i = 0; i < included.size(); i++)
				if (included.getBooleanVariable(i).getValue())
					System.out.println(C.getBitString(i).toBits());
		}
		else
			System.out.println("No solution.");
	}
}
