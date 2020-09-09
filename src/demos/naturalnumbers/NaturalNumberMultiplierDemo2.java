package demos.naturalnumbers;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.NaturalNumberMultiplier;

public class NaturalNumberMultiplierDemo2
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumber X = new NaturalNumber("X");
		INaturalNumber Y = new NaturalNumber("Y");
		INaturalNumber Z = new NaturalNumber("Z");

		IProblem p = new Conjunction(new IProblem[]
		{ new NaturalNumberMultiplier(X, Y, Z), new NaturalNumberFixer(X, 8), new NaturalNumberFixer(Y, 3), });

		IProblemMessage s = p.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
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