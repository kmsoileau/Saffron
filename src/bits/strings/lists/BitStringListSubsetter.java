/*
 * BitStringListSubsetter.java	1.0 05/04/11
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */

package bits.strings.lists;

import bits.Conjunction;
import bits.IProblem;
import bits.Problem;
import bits.strings.lists.exceptions.BitStringListException;

public class BitStringListSubsetter extends Problem implements IProblem
{
	// Ensures that every IBitString in A is also in B
	public BitStringListSubsetter(IBitStringList A, IBitStringList B)
			throws Exception
	{
		if (A == null)
			throw new BitStringListException(
					"Passed a null IBitStringList to constructor.");
		if (B == null)
			throw new BitStringListException(
					"Passed a null IBitStringList to constructor.");

		IProblem res[] = new IProblem[A.size()];
		for (int i = 0; i < A.size(); i++)
			res[i] = new BitStringListMembershipper(A.getBitString(i), B);
		IProblem total = new Conjunction(res);
		total = new Conjunction(total, new BitStringListNonrepeater(A),
				new BitStringListNonrepeater(B));
		this.setClauses(total.getClauses());
	}
}