package demos.naturalnumbers;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.NaturalNumberLeastCommonMultipler;

public class NaturalNumberLeastCommonMultiplerDemo
{
	public static void main(String[] args) throws Exception
	{
		NaturalNumber.setLength(9);
		INaturalNumber X = new NaturalNumber("X");
		INaturalNumber Y = new NaturalNumber("Y");

		INaturalNumber LCM = new NaturalNumber();

		IProblem p = new Conjunction(new IProblem[]
		{ new NaturalNumberFixer(X, 19), new NaturalNumberFixer(Y, 16),
				new NaturalNumberLeastCommonMultipler(X, Y, LCM) });

		System.out.println(p);

		IProblemMessage s = p.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("X = " + X);
			System.out.println("Y = " + Y);
			System.out.println("LCM = " + LCM);
		}
		else
			System.out.println("No solution.");
	}
}