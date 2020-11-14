/*
 * BitArrayTotaler.java	1.0 2019/02/23
 *
 * Copyright 2019 Positronic Software.
 *
 *
 */

package naturalnumbers;

import java.util.ArrayList;

import bits.Conjunction;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import bits.IProblem;

public class BitArrayTotaler extends bits.Problem implements IProblem
{
	public BitArrayTotaler(IBooleanVariable[] X, INaturalNumber bitSum) throws Exception
	{
		INaturalNumber[] allOnes = new NaturalNumber[X.length];
		ArrayList<IProblem> p = new ArrayList<IProblem>();
		for (int i = 0; i < X.length; i++)
		{
			allOnes[i] = new NaturalNumber();
			p.add(new NaturalNumberFixer(allOnes[i], 1L));
		}

		IProblem problem = new Conjunction(new Conjunction(p), new ConditionalAdder(allOnes, X, bitSum));

		this.setClauses(problem.getClauses());
	}
}