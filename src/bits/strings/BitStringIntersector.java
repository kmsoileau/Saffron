/*
 * BitStringDominatora	1.0 2018/12/04
 *
 * Copyright 2018 Positronic Software.
 *
 *
 */

package bits.strings;

import bits.BitFixer;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBitString;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.strings.exceptions.BitStringIntersectorException;

/**
 * Satisfied when X[i]==true and Y[i]==true for some i.
 */
public class BitStringIntersector extends bits.Problem implements IProblem
{
	public BitStringIntersector(IBitString X, IBitString Y) throws Exception
	{
		if (X.size() != Y.size())
			throw new BitStringIntersectorException("X and Y are not of equal size.");
		else
		{
			int commonsize = X.size();
			IProblem[] c = new IProblem[commonsize];
			for (int i = 0; i < commonsize; i++)
			{
				IBooleanVariable currX = X.getBooleanVariable(i);
				IBooleanVariable currY = Y.getBooleanVariable(i);
				c[i] = new Conjunction(new BitFixer(currX, true), new BitFixer(currY, true));
			}

			this.setClauses(new Disjunction(c).getClauses());
		}
	}
}