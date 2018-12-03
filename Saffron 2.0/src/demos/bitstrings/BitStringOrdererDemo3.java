/*
 * BitStringOrdererDemo3.java	1.0 05/10/25
 *
 * Copyright 2005 Positronic Software.
 *
 *
 */

package demos.bitstrings;

import java.util.List;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IBooleanLiteral;
import bits.IProblem;
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
		
		System.out.println(problem.toDIMACS());

		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("X= " + X);
			System.out.println("Y= " + Y);
		}
		else
			System.out.println("No solution.");
	}
}