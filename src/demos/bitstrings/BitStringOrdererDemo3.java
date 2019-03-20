/*
 * BitStringOrdererDemo3.java	1.0 05/10/25
 *
 * Copyright 2005 Positronic Software.
 *
 *
 */

package demos.bitstrings;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bitstrings.BitString;
import bitstrings.BitStringFixer;
import bitstrings.BitStringOrderer;

public class BitStringOrdererDemo3
{
	public static void main(String[] args) throws Exception
	{
		IBitString X = new BitString("X", "101");
		System.out.println(X);
		IProblem fixX = new BitStringFixer(X);
		System.out.println(fixX);

		IBitString Y = new BitString("Y", "10110");
		System.out.println(Y);
		IProblem fixY = new BitStringFixer(Y);
		System.out.println(fixY);

		IProblem bta = new BitStringOrderer(X, Y);
		System.out.println(bta);

		IProblem problem = new Conjunction(fixX, fixY, bta);
		System.out.println(problem);

		System.out.println(((Problem) problem).toDIMACS());

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE
				&& s.getLiterals().size() > 0)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("X= " + X);
			System.out.println("Y= " + Y);
		}
		else
			System.out.println("No solution.");
	}
}