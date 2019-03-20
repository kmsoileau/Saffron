package demos.naturalnumbers;

import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.NaturalNumberMultiplier;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

public class NaturalNumberMultiplierDemo3
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumber X = new NaturalNumber("X");
		INaturalNumber Y = new NaturalNumber("Y");
		INaturalNumber Z = new NaturalNumber("Z");

		IProblem p;
		IProblemMessage s;
		for (long i = 1; i < (long) (Math.pow(2., NaturalNumber.getLength())); i++)
			for (long j = 0; i * j < (long) Math.pow(2.,
					NaturalNumber.getLength()); j++)
			{
				p = new Conjunction(new IProblem[]
				{ new NaturalNumberMultiplier(X, Y, Z),
						new NaturalNumberFixer(X, i),
						new NaturalNumberFixer(Y, j), });

				s = p.findModel(Problem.defaultSolver());
				if (s.getStatus() == IProblemMessage.SATISFIABLE
						&& s.getLiterals().size() > 0)
				{
					BooleanLiteral.interpret(s.getLiterals());
					System.out.print("\nX = " + X);
					System.out.print("\tY = " + Y);
					System.out.print("\tZ = " + Z);
				}
				else
					System.out.println("No solution.");
			}
	}
}