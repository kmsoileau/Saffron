/*
 * BitTableNoter.java	1.0 05/04/28
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */

package bits.tables;

import bits.BitNoter;
import bits.Conjunction;
import bits.IProblem;
import bits.Problem;

public class BitTableNoter extends Problem implements IProblem
{
	public BitTableNoter(IBitTable X, IBitTable Y) throws Exception
	{
		if (!X.isSameSizeAs(Y))
			this.setClauses(Problem.unsolvableProblem().getClauses());
		else
		{
			IProblem[] p = new IProblem[X.numberRows() * X.numberColumns()];
			int count = 0;
			for (int i = 0; i < X.numberRows(); i++)
				for (int j = 0; j < X.numberColumns(); j++)
					p[count++] = new BitNoter(X.getBooleanVariable(i, j),
							Y.getBooleanVariable(i, j));
			this.setClauses(new Conjunction(p).getClauses());
		}
	}
}