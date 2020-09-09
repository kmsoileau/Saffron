/*
 * BitStringOrdererDemo2.java	1.0 05/05/04
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

public class BitStringOrdererDemo2
{
	public static void main(String[] args) throws Exception
	{
		int degreeX = 6;
		int degreeY = 4;
		ArrayList<IProblem> pfix = new ArrayList<IProblem>();

		IBitString X = new BitString("X", new IBooleanVariable[degreeX]);
		for (int i = 0; i < X.size(); i++)
		{
			boolean value;
			if (Math.random() < .5)
				value = true;
			else
				value = false;
			X.setBooleanVariable(i, BooleanVariable.getBooleanVariable(X.getName() + "_" + i));
			X.getBooleanVariable(i).setValue(value);
			pfix.add(new BitFixer(X.getBooleanVariable(i), value));
		}

		System.out.println(X);

		IBitString Y = new BitString("Y", new IBooleanVariable[degreeY]);
		for (int i = 0; i < Y.size(); i++)
		{
			boolean value;
			if (Math.random() < .5)
				value = true;
			else
				value = false;
			Y.setBooleanVariable(i, BooleanVariable.getBooleanVariable(Y.getName() + "_" + i));
			Y.getBooleanVariable(i).setValue(value);
			pfix.add(new BitFixer(Y.getBooleanVariable(i), value));
		}

		System.out.println(Y);

		IProblem fix = new Conjunction(pfix);

		IProblem bta = new BitStringOrderer(X, Y);
		System.out.println(bta);

		IProblem problem = new Conjunction(fix, bta);

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