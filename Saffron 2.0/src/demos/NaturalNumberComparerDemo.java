/*
 * NaturalNumberComparerDemo.java	1.0 14/06/02
 *
 * Copyright 2014 Positronic Software.
 *
 *
 */
/**
 * <p>Title: NaturalNumberComparerDemo</p>
 * <p>Description: A demonstration of the use of the NaturalNumberComparer
 * object.</p>
 * <p>Copyright (c) 2014</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */

package demos;

import java.util.List;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberComparer;
import naturalnumbers.NaturalNumberFixer;

public class NaturalNumberComparerDemo
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumber X = new NaturalNumber("X");
		INaturalNumber Y = new NaturalNumber("Y");

		NaturalNumberFixer bnnfx = new NaturalNumberFixer(X, 19);
		NaturalNumberFixer bnnfy = new NaturalNumberFixer(Y, 19);

		NaturalNumberComparer compare = new NaturalNumberComparer(X, Y);

		IProblem p = new Conjunction(new Conjunction(bnnfx, bnnfy), compare);

		System.out.println(p);
		List<IBooleanLiteral> s = p.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("X = " + X);
			System.out.println("Y = " + Y);
		} else
			System.out.println("No solution.");
	}
}