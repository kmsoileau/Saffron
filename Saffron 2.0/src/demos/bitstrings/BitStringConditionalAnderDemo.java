package demos.bitstrings;

import java.util.List;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import bitstrings.BitString;
import bitstrings.BitStringConditionalAnder;
import bitstrings.BitStringFixer;

public class BitStringConditionalAnderDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitString[] X = new IBitString[]
		{ new BitString("01001010"), new BitString("10101010"),
				new BitString("00001000"), new BitString("00100000") };

		IBitString conditionalResult = new BitString(X[0].size());
		IBitString membership = new BitString("1010");
		IProblem problem = new Conjunction(new BitStringFixer(X),
				new BitStringFixer(membership), new BitStringConditionalAnder(
						X, membership, conditionalResult));

		List<?> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
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
