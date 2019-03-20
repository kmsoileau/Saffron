package demos.naturalnumbers;

import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.NaturalNumberIncrementer;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

/**
 * <p>
 * Title: TBS
 * </p>
 * <p>
 * Description: TBS
 * </p>
 * <p>
 * Copyright (c) 2005
 * </p>
 * <p>
 * Company: Positronic Software
 * </p>
 * 
 * @author Kerry Michael Soileau
 * @version 1.0
 */
public class NaturalNumberIncrementerDemo
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumber X = new NaturalNumber("X");
		INaturalNumber Y = new NaturalNumber("Y");
		INaturalNumber C = new NaturalNumber("C");

		IProblem bnnfx = new NaturalNumberFixer(X, 17);
		IProblem bnnfy = new NaturalNumberIncrementer(X, Y, C);

		IProblem p = new Conjunction(bnnfx, bnnfy);

		System.out.println(p);
		IProblemMessage s = p.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE
				&& s.getLiterals().size() > 0)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("X= " + X);
			System.out.println("Y= " + Y);
			System.out.println("C= " + C);
		}
		else
			System.out.println("No solution.");
	}
}