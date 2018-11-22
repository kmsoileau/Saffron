/**
 * 
 * ConditionalAdderDemo.java	1.0 2018/11/05
 * Copyright 2018 Positronic Software
 * @author Kerry Michael Soileau
 * @version 1.0
 */

package demos;

import java.util.List;

import bits.BitFixer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.ConditionalAdder;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;

public class ConditionalAdderDemo1
{
	public static void main(String[] args) throws Exception
	{
		NaturalNumber.setLargestNaturalNumber(200);

		INaturalNumber A = new NaturalNumber("A");
		INaturalNumber B = new NaturalNumber("B");
		INaturalNumber C = new NaturalNumber("C");
		INaturalNumber D = new NaturalNumber("D");

		IBooleanVariable W = BooleanVariable.getBooleanVariable("W");
		IBooleanVariable X = BooleanVariable.getBooleanVariable("X");
		IBooleanVariable Y = BooleanVariable.getBooleanVariable("Y");
		IBooleanVariable Z = BooleanVariable.getBooleanVariable("Z");

		INaturalNumber CSUM = new NaturalNumber("CSUM");

		IProblem p = new Conjunction(new IProblem[]
		{ new NaturalNumberFixer(A, 127), new NaturalNumberFixer(B, 121),
				new NaturalNumberFixer(C, 37), new NaturalNumberFixer(D, 12),
				new ConditionalAdder(new INaturalNumber[]
				{ A, B, C, D }, new IBooleanVariable[]
				{ W, X, Y, Z }, CSUM), new BitFixer(W, false),
				new BitFixer(X, true), new BitFixer(Y, false),
				new BitFixer(Z, true) });

		List<IBooleanLiteral> s = p.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("A = " + A);
			System.out.println("B = " + B);
			System.out.println("C = " + C);
			System.out.println("D = " + D);
			System.out.println("W = " + W.getValue());
			System.out.println("X = " + X.getValue());
			System.out.println("Y = " + Y.getValue());
			System.out.println("Z = " + Z.getValue());
			System.out.println("CSUM = " + CSUM);
			BooleanLiteral.reset(s);
		}
		else
			System.out.println("No solution.");
	}
}