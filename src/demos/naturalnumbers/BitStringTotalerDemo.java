package demos.naturalnumbers;

import java.util.List;

import naturalnumbers.BitStringTotaler;
import naturalnumbers.NaturalNumber;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IBooleanLiteral;
import bits.INaturalNumber;
import bits.Problem;
import bitstrings.BitString;
import bitstrings.BitStringFixer;

public class BitStringTotalerDemo
{
	public static void main(String[] args) throws Exception
	{
		String testString = "01111011100011010";

		NaturalNumber.setLength(testString.length());

		INaturalNumber sum = new NaturalNumber();

		IBitString X = new BitString("X", testString);

		List<IBooleanLiteral> s = new Conjunction(new BitStringFixer(X),
				new BitStringTotaler(X, sum))
				.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("sum= " + sum);
		}
		else
			System.out.println("No solution.");
	}
}