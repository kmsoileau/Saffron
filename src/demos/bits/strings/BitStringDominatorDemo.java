/*
 * BitStringDominatorDemo.java	1.0 2018/12/04
 *
 * Copyright 2018 Positronic Software.
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
import bits.strings.BitStringDominator;
import bits.strings.BitStringFixer;

public class BitStringDominatorDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitString X = new BitString("0101");
		IBitString Y = new BitString("0111");

		IProblem problem = new Conjunction(new BitStringFixer(X), new BitStringFixer(Y), new BitStringDominator(X, Y));

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