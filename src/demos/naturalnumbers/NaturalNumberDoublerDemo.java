package demos.naturalnumbers;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
/**
 * <p>Title: TBS</p>
 * <p>Description: TBS</p>
 * <p>Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberDoubler;
import naturalnumbers.NaturalNumberFixer;

public class NaturalNumberDoublerDemo
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumber X = new NaturalNumber("X");
		INaturalNumber Y = new NaturalNumber("Y");

		for (long i = 0; i < Math.pow(2., 1. * NaturalNumber.getLength()) / 2; i++)
		{
			NaturalNumberFixer bnnfx = new NaturalNumberFixer(X, i);

			NaturalNumberDoubler NaturalNumberDoubler1 = new NaturalNumberDoubler(
					X, Y);

			IProblem p = new Conjunction(bnnfx, NaturalNumberDoubler1);
			IProblemMessage s = p.findModel(Problem.defaultSolver());
			if (s.getStatus() == IProblemMessage.SATISFIABLE)
			{
				BooleanLiteral.interpret(s.getLiterals());
				System.out.print("\nX= " + X);
				System.out.print("\tY= " + Y);
			}
			else
				System.out.print("No solution.");
		}
	}
}