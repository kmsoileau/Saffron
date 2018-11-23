/*
 * BitStringAnder.java	1.0 05/04/15
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */

package bitstrings;

import bits.BitAnder;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import bitstrings.exceptions.BitStringAnderException;

public class BitStringAnder extends Problem implements IProblem
{
	public BitStringAnder(IBitString X, IBitString Y, IBitString Z)
			throws Exception
	{
		if ((X.size() != Y.size()) || (X.size() != Z.size()))
			throw new BitStringAnderException(
					"X, Y and Z are not of equal size.");
		else
		{
			int commonsize = X.size();
			IProblem[] p = new IProblem[commonsize];
			int count = 0;
			for (int i = 0; i < commonsize; i++)
				p[count++] = new BitAnder(X.getBooleanVariable(i),
						Y.getBooleanVariable(i), Z.getBooleanVariable(i));
			this.setClauses(new Conjunction(p).getClauses());
		}
	}
}