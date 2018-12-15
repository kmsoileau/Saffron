/*
 * BitStringDominatora	1.0 2018/12/04
 *
 * Copyright 2018 Positronic Software.
 *
 *
 */

package bitstrings;

import bits.Clause;
import bits.IBitString;
import bits.IClause;
import bits.IProblem;
import bits.Problem;
import exceptions.bitstrings.BitStringDominatorException;

/*
 * Satisfied when X[i]==true implies Y[i]==true for every i.
 */
public class BitStringDominator extends Problem implements IProblem
{
	public BitStringDominator(IBitString X, IBitString Y) throws Exception
	{
		if (X.size() != Y.size())
			throw new BitStringDominatorException(
					"X and Y are not of equal size.");
		else
		{
			int commonsize = X.size();
			IClause[] c = new IClause[commonsize];
			for (int i = 0; i < X.size(); i++)
				c[i] = Clause.newClause().orNot(X.getBooleanVariable(i))
						.or(Y.getBooleanVariable(i));
			this.setClauses(c);
		}
	}
}