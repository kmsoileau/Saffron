package showcase.hittingset;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.BitStringFixer;
import bits.strings.HittingBitStringer;
import utility.RandomBitString;

public class HittingBitStringerDemo2
{
	public static void main(String[] args) throws Exception
	{
		int K = 3;

		IBitString[] C = new IBitString[20];
		for (int i = 0; i < C.length; i++)
			C[i] = new BitString(RandomBitString.randomBitstring(8));

		IBitString Y = new BitString(C[0].size());

		IProblem problem = new Conjunction(new BitStringFixer(C), new HittingBitStringer(C, K, Y));

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			for (int i = 0; i < C.length; i++)
				System.out.println(C[i].toBits());
			System.out.println("\n" + Y.toBits());
		}
		else
			System.out.println("No solution.");
	}
}
