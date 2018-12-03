/*
 * BitStringSetter.java	1.0 05/04/15
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */

package bitstrings;

import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import exceptions.bitstrings.BitStringSetterException;

public class BitStringSetter extends Problem implements IProblem
{
	public BitStringSetter(IBitString X, IBitString Y) throws Exception
	{
		if (X.size() != Y.size())
			throw new BitStringSetterException("X and Y are not of equal size.");
		// this.setClauses(Problem.unsolvableProblem().getClauses());
		else
		{
			int commonsize = X.size();
			IProblem[] p = new IProblem[commonsize];
			for (int i = 0; i < commonsize; i++)
				p[i] = new BitStringBitFixer(Y, i, true);
			this.setClauses(new Conjunction(p).getClauses());
		}
	}
}