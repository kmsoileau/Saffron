package demos.bitstrings;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bitstrings.BitString;
import bitstrings.BitStringConditionalOrer;
import bitstrings.BitStringFixer;

public class BitStringConditionalOrerDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitString[] X = new IBitString[]
		{ new BitString("01001010"), new BitString("10101010"),
				new BitString("00001000"), new BitString("00100000") };

		IBitString conditionalResult = new BitString(X[0].size());
		IBitString membership = new BitString("1010");
		IProblem problem = new Conjunction(new BitStringFixer(X),
				new BitStringFixer(membership), new BitStringConditionalOrer(X,
						membership, conditionalResult));

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE
				&& s.getLiterals().size() > 0)
		{
			BooleanLiteral.interpret(s.getLiterals());
			for (int i = 0; i < X.length; i++)
				if (membership.getBooleanVariable(i).getValue())
					System.out.println(X[i].toBits());
			System.out.println("\nSolution\n--------");
			System.out.println(conditionalResult.toBits());
		}
		else
			System.out.println("There is no solution.");
	}
}
