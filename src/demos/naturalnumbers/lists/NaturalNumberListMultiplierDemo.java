package demos.naturalnumbers.lists;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.NaturalNumberMultiplier;
import naturalnumbers.lists.INaturalNumberList;
import naturalnumbers.lists.NaturalNumberList;

public class NaturalNumberListMultiplierDemo
{
	public static void main(String[] args) throws Exception
	{
		NaturalNumber.setLargestNaturalNumber(54340);

		INaturalNumber W = new NaturalNumber("W");
		INaturalNumber X = new NaturalNumber("X");
		INaturalNumber Y = new NaturalNumber("Y");
		INaturalNumber Z = new NaturalNumber("Z");

		INaturalNumber P = new NaturalNumber("P");

		INaturalNumberList addend = new NaturalNumberList(new INaturalNumber[]
		{ W, X, Y, Z });

		IProblem p = new Conjunction(new IProblem[]
		{ new NaturalNumberMultiplier(addend, P), new NaturalNumberFixer(W, 13L), new NaturalNumberFixer(X, 19L),
				new NaturalNumberFixer(Y, 11L), new NaturalNumberFixer(Z, 20L) });

		IProblemMessage s = p.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());

			System.out.println("W = " + W);
			System.out.println("X = " + X);
			System.out.println("Y = " + Y);
			System.out.println("Z = " + Z);
			System.out.println("P = " + P);
			System.out.println("addend = " + addend);
		}
		else
			System.out.println("No solution.");
	}
}