/*
 * NaturalNumberTableAnder.java	1.0 05/11/17
 *
 * Copyright 2005 Positronic Software.
 *
 *
 */

package naturalnumbertables;

import bits.Conjunction;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.NaturalNumberAnder;
import naturalnumbertables.exceptions.NaturalNumberTableAnderException;

public class NaturalNumberTableAnder extends Problem implements IProblem
{
	public NaturalNumberTableAnder(INaturalNumberTable X,
			INaturalNumberTable Y, INaturalNumberTable Z) throws Exception
	{
		if (!X.isSameSizeAs(Y) || !X.isSameSizeAs(Z))
			throw new NaturalNumberTableAnderException(
					"INaturalNumberTables of differing sizes were passed to a constructor.");
		else
		{
			IProblem[] p = new IProblem[X.getNumberOfRows()
					* X.getNumberOfColumns()];
			int count = 0;
			for (int i = 0; i < X.getNumberOfRows(); i++)
				for (int j = 0; j < X.getNumberOfColumns(); j++)
					p[count++] = new NaturalNumberAnder(
							X.getNaturalNumber(i, j), Y.getNaturalNumber(i, j),
							Z.getNaturalNumber(i, j));
			this.setClauses(new Conjunction(p).getClauses());
		}
	}
}