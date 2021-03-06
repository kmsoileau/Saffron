/*
 * BitStringOrdererDemo.java	1.0 05/05/04
 *
 * Copyright 2005 Positronic Software.
 *
 *
 */

package demos.bits.strings;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.BitStringFixer;
import bits.strings.BitStringNonDominator;

public class BitStringNonDominatorDemo
{
	static int degree = 4;

	public static void main(String[] args) throws Exception
	{
		IBitString X = new BitString("0101");
		IBitString Y = new BitString("1101");

		IProblem problem = new Conjunction(new BitStringFixer(X), new BitStringFixer(Y),
				new BitStringNonDominator(X, Y));

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
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