package demos.bitstrings;

import java.util.List;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import bitstrings.BitString;
import bitstrings.BitStringFixer;
import bitstrings.ConditionalOrer;

public class ConditionalOrerDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitString[] X = new IBitString[]
		{ new BitString("01001010"), new BitString("10101010"),
				new BitString("00001000"), new BitString("00100000") };

		IBitString conditionalResult = new BitString(X[0].size());
		IBitString membership = new BitString("1010");
		IProblem problem = new Conjunction(new BitStringFixer(X),
				new BitStringFixer(membership), new ConditionalOrer(X,
						membership, conditionalResult));

		List<?> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			for (int i = 0; i < X.length; i++)
				System.out.println(X[i]);
			System.out.println(membership);
			System.out.println(conditionalResult);
		}
		else
			System.out.println("There is no solution.");
	}
}
