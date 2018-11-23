/*
 * BitStringBitFixer.java	1.0 05/04/15
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */

package bitstrings;

import bits.BitFixer;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import bitstrings.exceptions.BitStringBitFixerException;

public class BitStringBitFixer extends Problem implements IProblem
{
	public BitStringBitFixer(IBitString b, int bit, boolean val)
			throws Exception
	{
		if ((bit < 0) || (b.size() - 1 < bit))
			throw new BitStringBitFixerException(
					"bit < 0 or b.size() - 1 < bit.");
		else
			this.setClauses(new BitFixer(b.getBooleanVariable(bit), val)
					.getClauses());
	}
}