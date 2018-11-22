/*
 * BitStringEqualizer.java	1.0 05/04/15
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */

package bitstrings;

import bits.BitEqualizer;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import bitstrings.exceptions.BitStringSubstringerException;

public class BitStringSubstringer extends Problem implements IProblem
{
	public BitStringSubstringer(final IBitString X, final IBitString Y)
			throws Exception
	{
		// final int smaller = X.size();
		int smaller = X.size();
		if (smaller > Y.size())
			throw new BitStringSubstringerException("X.size() > Y.size()");
		// this.setClauses(Problem.unsolvableProblem().getClauses());
		else
		{
			IProblem problem = null;
			for (int begin = 0; begin < Y.size() - X.size() + 1; begin++)
			{
				final IProblem[] p = new IProblem[smaller];
				int count = 0;
				for (int i = 0; i < smaller; i++)
					p[count++] = new BitEqualizer(X.getBooleanVariable(i),
							Y.getBooleanVariable(begin + i));
				problem = new Disjunction(problem, new Conjunction(p));
			}
			this.setClauses(problem.getClauses());
		}
	}
}