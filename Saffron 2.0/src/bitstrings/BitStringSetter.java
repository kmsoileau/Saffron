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

public class BitStringSetter extends Problem implements IProblem
{
	private static final long serialVersionUID = 3168288931301380202L;

	public BitStringSetter(IBitString X, IBitString Y) throws Exception
	{
		if (X.size() != Y.size())
			this.setClauses(Problem.unsolvableProblem().getClauses());
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