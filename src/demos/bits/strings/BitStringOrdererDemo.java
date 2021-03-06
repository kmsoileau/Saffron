/*
 * BitStringOrdererDemo.java	1.0 05/05/04
 *
 * Copyright 2005 Positronic Software.
 *
 *
 */

package demos.bits.strings;

import java.util.ArrayList;

import bits.BitFixer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.IBitString;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.BitStringOrderer;

public class BitStringOrdererDemo
{
	static int degree = 4;

	public static void main(String[] args) throws Exception
	{
		IBitString X = new BitString("X", new IBooleanVariable[degree]);
		ArrayList<IProblem> pfix = new ArrayList<IProblem>();
		for (int i = 0; i < X.size(); i++)
		{
			boolean value;
			if (Math.random() < .5)
				value = true;
			else
				value = false;
			X.setBooleanVariable(i, BooleanVariable.getBooleanVariable(X.getName() + "_" + i));
			pfix.add(new BitFixer(X.getBooleanVariable(i), value));
		}

		IBitString Y = new BitString("Y", new IBooleanVariable[degree]);
		for (int i = 0; i < Y.size(); i++)
			Y.setBooleanVariable(i, BooleanVariable.getBooleanVariable(Y.getName() + "_" + i));

		IProblem fix = new Conjunction(pfix);
		// System.out.println(fix);

		IProblem bta = new BitStringOrderer(X, Y);
		System.out.println(bta);

		IProblem problem = new Conjunction(fix, bta);
		// System.out.println(problem);
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