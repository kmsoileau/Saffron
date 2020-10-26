package demos.naturalnumbers;

import bits.BooleanLiteral;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberAbsoluteDifferencer;
import naturalnumbers.NaturalNumberFixer;

public class NaturalNumberAbsoluteDifferencerDemo
{
	public static void main(String[] args) throws Exception
	{
		NaturalNumber.setLargestNaturalNumber(100);
		INaturalNumber X = new NaturalNumber("X");
		INaturalNumber Y = new NaturalNumber("Y");
		INaturalNumber A = new NaturalNumber("A");

		IProblem p = new NaturalNumberFixer(X, 17).and(new NaturalNumberFixer(Y, 43))
				.and(new NaturalNumberAbsoluteDifferencer(X, Y, A));

		IProblemMessage s = p.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("X = " + X);
			System.out.println("Y = " + Y);
			System.out.println("A = " + A);
		}
		else
			System.out.println("No solution.");
	}
}