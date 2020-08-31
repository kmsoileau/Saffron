package demos.naturalnumbers;

import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.NaturalNumberLinearDivisibilitor;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

/**
 * <p>
 * Title: NaturalNumberLinearDivisibilitorDemo
 * </p>
 * <p>
 * Description: TBS
 * </p>
 * <p>
 * Copyright (c) 2010
 * </p>
 * <p>
 * Company: Positronic Software
 * </p>
 * 
 * @author Kerry Michael Soileau
 * @version 1.0
 */

public class NaturalNumberLinearDivisibilitorDemo
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumber A = new NaturalNumber("A");
		INaturalNumber X = new NaturalNumber("X");
		INaturalNumber C = new NaturalNumber("C");

		IProblem p = new Conjunction(new NaturalNumberFixer(A, 3),
				new NaturalNumberFixer(C, 35),
				new NaturalNumberLinearDivisibilitor(A, X, C));

		System.out.println(p);

		IProblemMessage s = p.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("A= " + A);
			System.out.println("X= " + X);
			System.out.println("C= " + C);
		}
		else
			System.out.println("No solution.");
	}
}