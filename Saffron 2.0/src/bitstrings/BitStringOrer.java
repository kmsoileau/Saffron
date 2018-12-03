/*
 * BitStringOrer.java	1.0 05/04/15
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */

package bitstrings;

import bits.BitOrer;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import exceptions.bitstrings.BitStringOrerException;

public class BitStringOrer extends Problem implements IProblem
{
	public BitStringOrer(IBitString X, IBitString Y, IBitString Z)
			throws Exception
	{
		if (X.size() != Y.size() || X.size() != Z.size())
			throw new BitStringOrerException(
					"X, Y and Z are not of equal size.");
		// this.setClauses(Problem.unsolvableProblem().getClauses());
		else
		{
			int commonsize = X.size();
			IProblem[] p = new IProblem[commonsize];
			int count = 0;
			for (int i = 0; i < commonsize; i++)
				p[count++] = new BitOrer(X.getBooleanVariable(i),
						Y.getBooleanVariable(i), Z.getBooleanVariable(i));
			this.setClauses(new Conjunction(p).getClauses());
		}
	}
}