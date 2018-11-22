/*
 * BitStringListFixer.java	1.0 05/04/11
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */

package bitstringlists;

import bits.BitFixer;
import bits.Conjunction;
import bits.IProblem;
import bits.Problem;
import bitstringlists.exceptions.BitStringListFixerException;

public class BitStringListFixer extends Problem implements IProblem
{
	public BitStringListFixer(IBitStringList target) throws Exception
	{
		if (target == null)
			throw new BitStringListFixerException(
					"Passed null IBitStringList to constructor.");
		IProblem problem = null;
		for (int i = 0; i < target.size(); i++)
			for (int j = 0; j < target.getBitString(i).size(); j++)
				problem = new Conjunction(problem, new BitFixer(target
						.getBitString(i).getBooleanVariable(j), target
						.getBitString(i).getBooleanVariable(j).getValue()));
		this.setClauses(problem.getClauses());
	}
}