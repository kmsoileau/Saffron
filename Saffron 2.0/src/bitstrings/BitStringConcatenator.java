/*
 * BitStringConcatenator.java	1.0 05/04/15
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */

package bitstrings;

import bits.BitEqualizer;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import bitstrings.exceptions.BitStringConcatenatorException;

public class BitStringConcatenator extends Problem implements IProblem
{
	public BitStringConcatenator(IBitString X, IBitString Y, IBitString Z)
			throws Exception
	{
		if (X.size() + Y.size() != Z.size())
			throw new BitStringConcatenatorException(
					"The sizes of X and Y don't add up to the size of Z.");
		// this.setClauses(Problem.unsolvableProblem().getClauses());
		else
		{
			IProblem[] p = new IProblem[Z.size()];
			int count = 0;
			for (int i = 0; i < X.size(); i++)
			{
				p[count] = new BitEqualizer(X.getBooleanVariable(i),
						Z.getBooleanVariable(count));
				count++;
			}
			for (int i = 0; i < Y.size(); i++)
			{
				p[count] = new BitEqualizer(Y.getBooleanVariable(i),
						Z.getBooleanVariable(count));
				count++;
			}
			this.setClauses(new Conjunction(p).getClauses());
		}
	}
}