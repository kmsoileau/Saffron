package naturalnumbers;

import java.util.List;

import bits.BitFixer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

public class NaturalNumberBitMultiplyDemo
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable b = BooleanVariable.getBooleanVariable("b");
		NaturalNumber.setLength(8);
		INaturalNumber X = new NaturalNumber("X");
		INaturalNumber Y = new NaturalNumber("Y");

		IProblem p = new Conjunction(new BitFixer(b, true),
				new NaturalNumberFixer(X, 255L), new NaturalNumberBitMultiply(
						b, X, Y));

		List<IBooleanLiteral> s = p.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("b= " + b.getValue());
			System.out.println("X= " + X);
			System.out.println("Y= " + Y);
		}
		else
			System.out.println("No solution.");
	}
}