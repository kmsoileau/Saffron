/*
 * BitStringDominatora	1.0 2018/12/04
 *
 * Copyright 2018 Positronic Software.
 *
 *
 */

package bits.strings.lists;

import bits.Conjunction;
import bits.Disjunction;
import bits.IProblem;
import bits.Problem;
import bits.strings.BitStringEqualizer;
import bits.strings.BitStringUnequalizer;
import bits.strings.lists.exceptions.BitStringListsSingularDifferencerException;

public class BitStringListsSingularDifferencer extends Problem implements
		IProblem
{
	public BitStringListsSingularDifferencer(IBitStringList B1,
			IBitStringList B2) throws Exception
	{
		if (B1.size() != B2.size())
			throw new BitStringListsSingularDifferencerException(
					"X and Y are not of equal size.");
		else
		{
			int len = B1.size();

			IProblem[] pOuter = new Problem[len];
			for (int dBit = 0; dBit < len; dBit++)
			{
				IProblem[] pInner = new IProblem[len];
				int index = 0;
				for (int j = 0; j < len; j++)
				{
					if (dBit == j)
						pInner[index++] = new BitStringUnequalizer(
								B1.getBitString(j), B2.getBitString(j));
					else
						pInner[index++] = new BitStringEqualizer(
								B1.getBitString(j), B2.getBitString(j));
				}
				pOuter[dBit] = new Conjunction(pInner);
			}
			IProblem problem = new Disjunction(pOuter);
			this.setClauses(problem.getClauses());
		}
	}
}