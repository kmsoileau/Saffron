/*
 * BitStringDominatora	1.0 2018/12/04
 *
 * Copyright 2018 Positronic Software.
 *
 *
 */

package bits.strings;

import bits.BitEqualizer;
import bits.BitUnequalizer;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import bits.strings.exceptions.BitStringsSingularDifferencerException;

public class BitStringsSingularDifferencer extends Problem implements IProblem
{
	public BitStringsSingularDifferencer(IBitString B1, IBitString B2)
			throws Exception
	{
		if (B1.size() != B2.size())
			throw new BitStringsSingularDifferencerException(
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
						pInner[index++] = new BitUnequalizer(
								B1.getBooleanVariable(j),
								B2.getBooleanVariable(j));
					else
						pInner[index++] = new BitEqualizer(
								B1.getBooleanVariable(j),
								B2.getBooleanVariable(j));
				}
				pOuter[dBit] = new Conjunction(pInner);
			}
			IProblem problem = new Disjunction(pOuter);
			this.setClauses(problem.getClauses());
		}
	}
}