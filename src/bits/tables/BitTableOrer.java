/*
 * BitTableOrer.java	1.0 05/04/28
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */

package bits.tables;

import bits.BitOrer;
import bits.Conjunction;
import bits.IProblem;
import bits.Problem;

public class BitTableOrer extends Problem implements IProblem
{
	public BitTableOrer(IBitTable X, IBitTable Y, IBitTable Z) throws Exception
	{
		if (!X.isSameSizeAs(Y) || !X.isSameSizeAs(Z))
			this.setClauses(Problem.unsolvableProblem().getClauses());
		else
		{
			IProblem[] p = new IProblem[X.numberRows() * X.numberColumns()];
			int count = 0;
			for (int i = 0; i < X.numberRows(); i++)
				for (int j = 0; j < X.numberColumns(); j++)
					p[count++] = new BitOrer(X.getBooleanVariable(i, j),
							Y.getBooleanVariable(i, j), Z.getBooleanVariable(i,
									j));
			this.setClauses(new Conjunction(p).getClauses());
		}
	}
}