package demos.naturalnumbers;

import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.NaturalNumberIncrementer;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

public class NaturalNumberIncrementerDemo3
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumber X = new NaturalNumber("X");
		INaturalNumber Y = new NaturalNumber("Y");
		// INaturalNumber C=new NaturalNumber("C");

		for (int i = 0; i < Math.pow(2., 1. * NaturalNumber.getLength()); i++)
		{
			IProblem bnnfx = new NaturalNumberFixer(X, i);
			IProblem bnnfy = new NaturalNumberIncrementer(X, Y);

			IProblem p = new Conjunction(bnnfx, bnnfy);

			IProblemMessage s = p.findModel(Problem.defaultSolver());
			if (s.getStatus() == IProblemMessage.SATISFIABLE)
			{
				BooleanLiteral.interpret(s.getLiterals());
				System.out.print("\nX= " + X + "\t");
				System.out.print("Y= " + Y + "\t");
			}
			else
				System.out.print("No solution.");
		}
	}
}