package demos.naturalnumbers;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.NaturalNumberIncrementer;

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
			IProblemMessage s = p.findModel(Problem.defaultSolver());
			if (s.getStatus() == IProblemMessage.SATISFIABLE)
			{
				BooleanLiteral.interpret(s.getLiterals());
				System.out.print("\nX= " + X + "\t");
				System.out.print("Y= " + Y + "\t");
				System.out.print("C= " + C + "\t");
			}
			else
				System.out.print("No solution.");
		}
	}
}