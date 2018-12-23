/*
 * BitStringZerothBitFixer.java	1.0 05/10/21
 *
 * Copyright 2005 Positronic Software.
 *
 *
 */

package bitstrings;

import bits.BitFixer;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;

public class BitStringZerothBitFixer extends Problem implements IProblem
{
	public BitStringZerothBitFixer(IBitString X, boolean b) throws Exception
	{
		this.setClauses(new BitFixer(X.getBooleanVariable(0), b).getClauses());
	}
}