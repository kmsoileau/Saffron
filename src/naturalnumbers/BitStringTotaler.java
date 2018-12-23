/*
 * BitStringTotaler.java	1.0 2018/12/04
 *
 * Copyright 2018 Positronic Software.
 *
 *
 */

package naturalnumbers;

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
		IProblem problem = Problem.newProblem();

		INaturalNumber[] allOnes = new NaturalNumber[X.size()];
		for (int i = 0; i < X.size(); i++)
		{
			allOnes[i] = new NaturalNumber();
			problem = new Conjunction(problem, new NaturalNumberFixer(
					allOnes[i], 1L));
		}

		problem = new Conjunction(problem, new ConditionalAdder(allOnes, X,
				bitSum));

		this.setClauses(problem.getClauses());
	}
}