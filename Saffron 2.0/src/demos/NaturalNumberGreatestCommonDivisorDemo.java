package demos;

import java.util.List;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.NaturalNumberGreatestCommonDivisor;

public class NaturalNumberGreatestCommonDivisorDemo
{
	public static void main(String[] args) throws Exception
	{
		NaturalNumber.setLength(8);
		INaturalNumber X = new NaturalNumber("X");
		INaturalNumber Y = new NaturalNumber("Y");

		INaturalNumber GCD = new NaturalNumber();

		IProblem p = new Conjunction(new IProblem[]
		{ new NaturalNumberFixer(X, 14), new NaturalNumberFixer(Y, 56),
				new NaturalNumberGreatestCommonDivisor(X, Y, GCD) });

		System.out.println(p);

		List<IBooleanLiteral> s = p.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("X = " + X);
			System.out.println("Y = " + Y);
			System.out.println("GCD = " + GCD);
		}
		else
			System.out.println("No solution.");
	}
}