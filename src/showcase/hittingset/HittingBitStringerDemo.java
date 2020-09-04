package showcase.hittingset;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.BitStringFixer;
import bits.strings.HittingBitStringer;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;

public class HittingBitStringerDemo
{
	public static void main(String[] args) throws Exception
	{
		int K = 3;

		IBitString[] C = new IBitString[20];
		for (int i = 0; i < C.length; i++)
			C[i] = new BitString(randomBitstring(8));

		IBitString Y = new BitString(C[0].size());

		INaturalNumber bitSum = new NaturalNumber(K);

		IProblem problem = new Conjunction(new BitStringFixer(C),
				new NaturalNumberFixer(bitSum), new HittingBitStringer(C,
						bitSum, Y));

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("INPUT\n");
			for (int i = 0; i < C.length; i++)
				System.out.println(C[i].toBits());
			System.out.println("\nSOLUTION\n");
			System.out.println(Y.toBits());
		}
		else
			System.out.println("No solution.");
	}

	static String randomBitstring(int n)
	{
		char[] bits = new char[n];
		for (int i = 0; i < n; i++)
			bits[i] = Math.random() > .5 ? '1' : '0';
		return new String(bits);
	}
}
