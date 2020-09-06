package bits.strings.lists;

import bits.Disjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import bits.strings.BitStringEqualizer;
import bits.strings.lists.exceptions.BitStringListException;

/*
 * BitStringListMembershipper.java	1.0 05/04/11
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */

public class BitStringListMembershipper extends Problem implements IProblem
{
	public BitStringListMembershipper(IBitString string, IBitStringList bsl)
			throws Exception
	{
		if (bsl == null)
			throw new BitStringListException(
					"Passed a null IBitStringList to constructor.");
		if (string == null)
			throw new BitStringListException(
					"Passed a null IBitString to constructor.");
		IProblem[] res = new IProblem[bsl.size()];
		for (int i = 0; i < bsl.size(); i++)
		{
			IBitString curr = bsl.getBitString(i);
			res[i] = new BitStringEqualizer(curr, string);
		}
		IProblem problem = new Disjunction(res);
		this.setClauses(problem.getClauses());
	}
}