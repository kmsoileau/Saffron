package showcase.bitstringcoverer;

import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bitstringlists.BitStringList;
import bitstringlists.BitStringListFixer;
import bitstringlists.BitStringSizedCoverer;
import bitstringlists.IBitStringList;
import bitstrings.BitString;

public class BitStringCovererDemo
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumber K = new NaturalNumber();

		IBitStringList C = new BitStringList(new IBitString[]
		{ new BitString("001101110001011"), new BitString("010100011000101"),
				new BitString("111011010001000"),
				new BitString("101010001111010") });
		IBitString included = new BitString(C.size());

		IProblem problem = new Conjunction(new BitStringListFixer(C),
				new NaturalNumberFixer(K, 3L), new BitStringSizedCoverer(C,
						included, K));

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE
				&& s.getLiterals().size() > 0)
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
