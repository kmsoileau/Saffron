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
import exceptions.bitstringlists.BitStringListFixerException;

/**
 * <p>
 * Title: BitStringListExchanger
 * </p>
 * <p>
 * Description: If the IProblem p=new BitStringListExchanger(A, B, m, n), then p
 * will be satisfied when A and B are equal as lists except possibly in
 * positions m and n. For these data, the member of A with index m will equal
 * the member of B with index n, and the member of B with index m will equal the
 * member of A with index n. In case m=n, p will be satisfied when A and B are
 * equal as lists.
 * </p>
 * <p>
 * Copyright (c) 2005
 * </p>
 * <p>
 * Company: Positronic Software
 * </p>
 * 
 * @author Kerry Michael Soileau
 * @version 1.0
 */
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