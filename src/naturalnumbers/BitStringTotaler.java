/*
 * BitStringTotaler.java	1.0 2018/12/04
 *
 * Copyright 2018 Positronic Software.
 *
 *
 */

package naturalnumbers;

import java.util.ArrayList;

import bits.Conjunction;
import bits.IBitString;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

public class BitStringTotaler extends Problem implements IProblem
{
	public BitStringTotaler(IBitString X, INaturalNumber bitSum)
			throws Exception
	{
		INaturalNumber[] allOnes = new NaturalNumber[X.size()];
		ArrayList<IProblem> p = new ArrayList<IProblem>();
		for (int i = 0; i < X.size(); i++)
		{
			allOnes[i] = new NaturalNumber();
			p.add(new NaturalNumberFixer(allOnes[i], 1L));
		}

		IProblem problem = new Conjunction(new Conjunction(p),
				new ConditionalAdder(allOnes, X, bitSum));

		this.setClauses(problem.getClauses());
	}
}