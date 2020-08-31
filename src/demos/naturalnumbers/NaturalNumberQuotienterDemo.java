package demos.naturalnumbers;

import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.NaturalNumberQuotienter;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

public class NaturalNumberQuotienterDemo
{
	public static void main(String[] args) throws Exception
	{
		NaturalNumber.setLength(8);
		INaturalNumber Dividend = new NaturalNumber("X");
		INaturalNumber Divisor = new NaturalNumber("Y");
		INaturalNumber Quotient = new NaturalNumber("Z");
		INaturalNumber Remainder = new NaturalNumber("C");

		IProblem p = new Conjunction(new IProblem[]
		{ new NaturalNumberQuotienter(Dividend, Divisor, Quotient, Remainder),
				new NaturalNumberFixer(Dividend, 203L),
				new NaturalNumberFixer(Divisor, 3L),
				 });

		System.out.println(p);

		IProblemMessage s = p.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("Dividend = " + Dividend);
			System.out.println("Divisor = " + Divisor);
			System.out.println("Quotient = " + Quotient);
			System.out.println("Remainder = " + Remainder);
		}
		else
			System.out.println("No solution.");
	}
}