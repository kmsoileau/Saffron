/*
 * BitTableXorer.java	1.0 05/04/15
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */

package bits.tables;

import bits.BitXorer;
import bits.Conjunction;
import bits.EnhancedProblem;
import bits.IProblem;
import bits.Problem;

public class BitTableXorer extends Problem implements IProblem
{
	public BitTableXorer(IBitTable X, IBitTable Y, IBitTable Z) throws Exception
	{
		if (!X.isSameSizeAs(Y) || !X.isSameSizeAs(Z))
			this.setClauses(EnhancedProblem.unsolvableProblem().getClauses());
		else
		{
			IProblem[] p = new IProblem[X.numberRows() * X.numberColumns()];
			int count = 0;
			for (int i = 0; i < X.numberRows(); i++)
				for (int j = 0; j < X.numberColumns(); j++)
					p[count++] = new BitXorer(X.getBooleanVariable(i, j), Y.getBooleanVariable(i, j),
							Z.getBooleanVariable(i, j));
			this.setClauses(new Conjunction(p).getClauses());
		}
	}
}