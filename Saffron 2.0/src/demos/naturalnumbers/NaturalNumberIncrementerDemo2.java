package demos.naturalnumbers;

import java.util.List;

import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.NaturalNumberIncrementer;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

public class NaturalNumberIncrementerDemo2
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumber X = new NaturalNumber("X");
		INaturalNumber Y = new NaturalNumber("Y");
		INaturalNumber C = new NaturalNumber("C");

		for (int i = 0; i < Math.pow(2., 1. * NaturalNumber.getLength()); i++)
		{
			IProblem bnnfx = new NaturalNumberFixer(X, i);
			IProblem bnnfy = new NaturalNumberIncrementer(X, Y, C);

			IProblem p = new Conjunction(bnnfx, bnnfy);

			// System.out.println(p);
			List<IBooleanLiteral> s = p.findModel(Problem.defaultSolver());
			if (s != null && s.size() > 0)
			{
				BooleanLiteral.interpret(s);
				System.out.print("\nX= " + X + "\t");
				System.out.print("Y= " + Y + "\t");
				System.out.print("C= " + C + "\t");
			}
			else
				System.out.print("No solution.");
		}
	}
}