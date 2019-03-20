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

public class NaturalNumberMultiplierDemo4
{
	public static void main(String[] args) throws Exception
	{
		NaturalNumber.setLargestNaturalNumber(21000);

		INaturalNumber X = new NaturalNumber("X");
		INaturalNumber Y = new NaturalNumber("Y");
		INaturalNumber Z = new NaturalNumber("Z");

		IProblemMessage s = new Conjunction(new IProblem[]
		{ new NaturalNumberMultiplier(X, Y, Z), new NaturalNumberFixer(X, 595),
				new NaturalNumberFixer(Y, 35), }).findModel(Problem
				.defaultSolver());
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