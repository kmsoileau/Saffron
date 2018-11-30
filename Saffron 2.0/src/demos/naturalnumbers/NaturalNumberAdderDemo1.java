/*
 * NaturalNumberAdderDemo.java	1.0 05/05/04
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */
/**
 * <p>Title: NaturalNumberAdderDemo</p>
 * <p>Description: A demonstration of the use of the NaturalNumberAdder
 * object.</p>
 * <p>Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */

package demos.naturalnumbers;

import java.util.List;

import bits.BooleanLiteral;
import bits.IBooleanLiteral;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberAdder;
import naturalnumbers.NaturalNumberFixer;

public class NaturalNumberAdderDemo1
{
	public static void main(String[] args) throws Exception
	{
		NaturalNumber.setLargestNaturalNumber(2);
		INaturalNumber X = new NaturalNumber("X");
		INaturalNumber Y = new NaturalNumber("Y");
		INaturalNumber Z = new NaturalNumber("Z");
		INaturalNumber C = new NaturalNumber("C");

		IProblem p = new NaturalNumberFixer(X, 1).and(
				new NaturalNumberFixer(Y, 1)).and(
				new NaturalNumberAdder(X, Y, Z, C));
		
		System.out.println(p);

		List<IBooleanLiteral> s = p.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("X = " + X);
			System.out.println("Y = " + Y);
			System.out.println("Z = " + Z);
			System.out.println("C = " + C);
		}
		else
			System.out.println("No solution.");
	}
}