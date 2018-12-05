/*
 * BitStringTotaler2.java	1.0 2018/12/05
 *
 * Copyright 2018 Positronic Software.
 *
 *
 */

package naturalnumbers;

import java.util.List;

import bits.BitFixer;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBitString;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import bitstrings.BitString;

public class BitStringTotaler2 extends Problem implements IProblem
{
	public BitStringTotaler2(IBitString X, INaturalNumber bitSum)
			throws Exception
	{
		IProblem problem;
		if (X.size() == 1)
		{
			// Edge Case
			problem = new Disjunction(new BitFixer(X.getBooleanVariable(0),
					false), new BitFixer(X.getBooleanVariable(0), true));
		}
		else
		{
			// General Case
			INaturalNumber sminus1 = new NaturalNumber();
			List<IBooleanVariable> reduced = X.asList().subList(1, X.size());
			IBitString reducedbs = new BitString(reduced);
			problem = new Conjunction(new NaturalNumberIncrementer(sminus1,
					bitSum), new Disjunction(new Conjunction(
					new BitStringTotaler2(reducedbs, bitSum), new BitFixer(
							X.getBooleanVariable(0), false)), new Conjunction(
					new BitStringTotaler2(reducedbs, sminus1), new BitFixer(
							X.getBooleanVariable(0), true))));
		}
		this.setClauses(problem.getClauses());
	}
}