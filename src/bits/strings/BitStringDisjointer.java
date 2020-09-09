/*
 * BitStringDominatora	1.0 2018/12/04
 *
 * Copyright 2018 Positronic Software.
 *
 *
 */

package bits.strings;

import bits.Clause;
import bits.IBitString;
import bits.IClause;
import bits.IProblem;
import bits.Problem;
import bits.strings.exceptions.BitStringDisjointerException;

/*
 * Satisfied when either X[i]==false or Y[i]==false for every i.
 */
public class BitStringDisjointer extends Problem implements IProblem
{
	public BitStringDisjointer(IBitString X, IBitString Y) throws Exception
	{
		if (X.size() != Y.size())
			throw new BitStringDisjointerException("X and Y are not of equal size.");
		else
		{
			int commonsize = X.size();
			IClause[] c = new IClause[commonsize];
			for (int i = 0; i < X.size(); i++)
				c[i] = Clause.newClause().orNot(X.getBooleanVariable(i)).orNot(Y.getBooleanVariable(i));
			this.setClauses(c);
		}
	}
}