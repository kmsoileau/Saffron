/*
 * BitTableInequalityIndicator.java	1.0 06/01/04
 *
 * Copyright 2006 Positronic Software.
 *
 *
 */

package bittables;

import bits.BitEqualizer;
import bits.Conjunction;
import bits.IProblem;
import bits.Problem;
import exceptions.bittables.BitTableInequalityIndicatorException;

public class BitTableInequalityIndicator extends Problem implements IProblem
{
	public BitTableInequalityIndicator(IBitTable X, IBitTable Y)
			throws Exception
	{
		if (!X.isSameSizeAs(Y))
			throw new BitTableInequalityIndicatorException(
					"IBitTables of differing sizes were passed to a constructor.");
		else
		{
			IProblem[] p = new IProblem[X.numberRows() * X.numberColumns()];
			int count = 0;
			for (int i = 0; i < X.numberRows(); i++)
				for (int j = 0; j < X.numberColumns(); j++)
					p[count++] = new BitEqualizer(X.getBooleanVariable(i, j),
							Y.getBooleanVariable(i, j));
			this.setClauses(new Conjunction(p).getClauses());
		}
	}
}