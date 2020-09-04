package demos.naturalnumbers;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;

public class NaturalNumberFixerDemo
{
	public static void main(String[] args) throws Exception
	{
		NaturalNumber.setLength(8);
		INaturalNumber X = new NaturalNumber("X");
		INaturalNumber Y = new NaturalNumber("Y");

		NaturalNumberFixer bnnfx = new NaturalNumberFixer(X, 129L);
		NaturalNumberFixer bnnfy = new NaturalNumberFixer(Y, 255L);

		Conjunction p7 = new Conjunction(bnnfx, bnnfy);

		IProblemMessage s = p7.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("X= " + X);
			System.out.println("Y= " + Y);
		}
		else
			System.out.println("No solution.");
	}
}