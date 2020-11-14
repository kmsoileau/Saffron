/*
 * BitTableAnder.java	1.0 05/04/28
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */

package bits.tables;

import bits.BitAnder;
import bits.Conjunction;
import bits.IProblem;
import bits.Problem;

public class BitTableAnder extends Problem implements IProblem
{
	public BitTableAnder(IBitTable bitTable1, IBitTable bitTable2, IBitTable bitTable3) throws Exception
	{
		if (!bitTable1.isSameSizeAs(bitTable2) || !bitTable1.isSameSizeAs(bitTable3))
			this.setClauses(unsolvableProblem().getClauses());
		else
		{
			IProblem[] p = new IProblem[bitTable1.numberRows() * bitTable1.numberColumns()];
			int count = 0;
			for (int i = 0; i < bitTable1.numberRows(); i++)
				for (int j = 0; j < bitTable1.numberColumns(); j++)
					p[count++] = new BitAnder(bitTable1.getBooleanVariable(i, j), bitTable2.getBooleanVariable(i, j),
							bitTable3.getBooleanVariable(i, j));
			this.setClauses(new Conjunction(p).getClauses());
		}
	}
}