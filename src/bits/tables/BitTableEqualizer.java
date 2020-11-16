/*
 * BitTableEqualizer.java	1.0 05/04/28
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */

package bits.tables;

import bits.BitEqualizer;
import bits.Conjunction;
import bits.EnhancedProblem;
import bits.IProblem;
import bits.Problem;

public class BitTableEqualizer extends Problem implements IProblem
{
	public BitTableEqualizer(IBitTable X, IBitTable Y) throws Exception
	{
		if (!X.isSameSizeAs(Y))
			this.setClauses(EnhancedProblem.unsolvableProblem().getClauses());
		else
		{
			IProblem[] p = new IProblem[X.numberRows() * X.numberColumns()];
			int count = 0;
			for (int i = 0; i < X.numberRows(); i++)
				for (int j = 0; j < X.numberColumns(); j++)
					p[count++] = new BitEqualizer(X.getBooleanVariable(i, j), Y.getBooleanVariable(i, j));
			this.setClauses(new Conjunction(p).getClauses());
		}
	}
}