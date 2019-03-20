package demos.naturalnumbers;

import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberBitMultiply;
import naturalnumbers.NaturalNumberFixer;
import bits.BitFixer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

public class NaturalNumberBitMultiplyDemo2
{
	public static void main(String[] args) throws Exception
	{
		NaturalNumber.setLength(8);
		IBooleanVariable[] b = new IBooleanVariable[4];
		INaturalNumber[] X = new INaturalNumber[4];
		INaturalNumber[] Y = new INaturalNumber[4];

		b[0] = BooleanVariable.getBooleanVariable("b0", true);
		b[1] = BooleanVariable.getBooleanVariable("b1", true);
		b[2] = BooleanVariable.getBooleanVariable("b2", false);
		b[3] = BooleanVariable.getBooleanVariable("b3", true);

		X[0] = new NaturalNumber("x0", 213L);
		X[1] = new NaturalNumber("x1", 123L);
		X[2] = new NaturalNumber("x2", 34L);
		X[3] = new NaturalNumber("x3", 2L);

		Y[0] = new NaturalNumber("y0");
		Y[1] = new NaturalNumber("y1");
		Y[2] = new NaturalNumber("y2");
		Y[3] = new NaturalNumber("y3");

		IProblem p = new Conjunction(new IProblem[]
		{ new BitFixer(b[0]), new BitFixer(b[1]), new BitFixer(b[2]),
				new BitFixer(b[3]), new NaturalNumberFixer(X[0]),
				new NaturalNumberFixer(X[1]), new NaturalNumberFixer(X[2]),
				new NaturalNumberFixer(X[3]),
				new NaturalNumberBitMultiply(b, X, Y) });

		IProblemMessage s = p.findModel(Problem.defaultSolver());

		if (s.getStatus() == IProblemMessage.SATISFIABLE
				&& s.getLiterals().size() > 0)
		{
			BooleanLiteral.interpret(s.getLiterals());
			for (int i = 0; i < b.length; i++)
			{
				System.out.println(b[i].getName() + "=" + b[i].getValue());
				System.out.println(X[i].getName() + "=" + X[i]);
				System.out.println(Y[i].getName() + "=" + Y[i]);
			}
		}
		else
			System.out.println("No solution.");
	}
}