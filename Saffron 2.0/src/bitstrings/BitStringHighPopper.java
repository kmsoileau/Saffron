/*
 * BitStringHighPopper.java	1.0 05/04/21
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
import exceptions.bitstrings.BitStringHighPopperException;

public class BitStringHighPopper extends Problem implements IProblem
{
	public BitStringHighPopper(IBitString X, IBitString Y) throws Exception
	{
		if (X.size() != Y.size() + 1)
			throw new BitStringHighPopperException("X.size() != Y.size() + 1");
		// this.setClauses(Problem.unsolvableProblem().getClauses());
		else
		{
			IProblem[] p = new IProblem[X.size()];
			int count = 0;
			for (int i = 0; i < Y.size(); i++)
				p[count++] = new BitEqualizer(X.getBooleanVariable(i),
						Y.getBooleanVariable(i));
			this.setClauses(new Conjunction(p).getClauses());
		}
	}
}