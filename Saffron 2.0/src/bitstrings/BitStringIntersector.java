/*
 * BitStringDominatora	1.0 2018/12/04
 *
 * Copyright 2018 Positronic Software.
 *
 *
 */

package bitstrings;

import exceptions.bitstrings.BitStringIntersectorException;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import bits.ProblemDenier;

/*
 * Satisfied when X[i]==true and Y[i]==true for somey i.
 */
public class BitStringIntersector extends Problem implements IProblem
{
	public BitStringIntersector(IBitString X, IBitString Y) throws Exception
	{
		if (X.size() != Y.size())
			throw new BitStringIntersectorException("X and Y are not of equal size.");
		else
		{
			this.setClauses(new ProblemDenier(new BitStringDisjointer(X,Y)).getClauses());
		}
	}
}