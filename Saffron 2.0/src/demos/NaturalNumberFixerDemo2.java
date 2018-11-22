package demos;

import java.util.List;

import bits.BooleanLiteral;
import bits.IBooleanLiteral;
import bits.INaturalNumber;
import bits.Problem;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;

public class NaturalNumberFixerDemo2
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumber X = new NaturalNumber("X");

		for (long i = 0; i < Math.pow(2., 1. * NaturalNumber.getLength()); i++)
		{
			List<IBooleanLiteral> s = new NaturalNumberFixer(X, i)
					.findModel(Problem.defaultSolver());
			if (s != null && s.size() > 0)
			{
				BooleanLiteral.interpret(s);
				System.out.print("\ni= " + i);
				System.out.print("\tX= " + X);
			}
			else
				System.out.print("No solution.");
		}
	}
}