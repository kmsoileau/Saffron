/*
 * BitTableFixer.java	1.0 05/04/15
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */

package bits.tables;

import bits.BitFixer;
import bits.Conjunction;
import bits.IProblem;
import bits.Problem;

public class BitTableFixer extends Problem implements IProblem
{
	public BitTableFixer(IBitTable bitTable) throws Exception
	{
		IProblem[] p = new BitFixer[bitTable.numberRows()
				* bitTable.numberColumns()];
		int count = 0;
		for (int i = 0; i < bitTable.numberRows(); i++)
			for (int j = 0; j < bitTable.numberColumns(); j++)
				p[count++] = new BitFixer(bitTable.getBooleanVariable(i, j));
		this.setClauses(new Conjunction(p).getClauses());
	}
}