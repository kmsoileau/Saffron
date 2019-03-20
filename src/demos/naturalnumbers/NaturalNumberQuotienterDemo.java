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
				new NaturalNumberFixer(Quotient, 194L),
				new NaturalNumberFixer(Remainder, 9L), });

		System.out.println(p);

		IProblemMessage s = p.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE
				&& s.getLiterals().size() > 0)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("X = " + Dividend);
			System.out.println("Y = " + Divisor);
			System.out.println("Z = " + Quotient);
			System.out.println("C = " + Remainder);
		}
		else
			System.out.println("No solution.");
	}
}