/*
 * BitStringListReverser.java	1.0 05/11/29
 *
 * Copyright 2005 Positronic Software.
 *
 *
 */
/**
 * Constrains two IBitStringLists to have the same entries in opposite 
 * order. Note: The IBitStringLists must have entries (IBitStrings) of
 * fixed size.
 *
 * @author  Kerry Michael Soileau
 * <blockquote><pre>
 * ksoileau2@yahoo.com
 * http://kerrysoileau.com/index.html
 * </pre></blockquote>
 * @version 1.0, 05/11/29
 * @see BitStringEqualizer
 * @see Conjunction
 * @see IProblem
 * @see Problem
 */

package bits.strings.lists;

import bits.Conjunction;
import bits.EnhancedProblem;
import bits.IProblem;
import bits.Problem;
import bits.strings.BitStringEqualizer;

public class BitStringListReverser extends Problem implements IProblem
{
	public BitStringListReverser(IBitStringList X, IBitStringList Y) throws Exception
	{
		if (X.size() != Y.size())
			this.setClauses(EnhancedProblem.unsolvableProblem().getClauses());
		else
		{
			int commonsize = X.size();
			IProblem[] p = new IProblem[commonsize];
			int count = 0;
			for (int i = 0; i < commonsize; i++)
				p[count++] = new BitStringEqualizer(X.getBitString(i), Y.getBitString(commonsize - 1 - i));

			this.setClauses(new Conjunction(p).getClauses());
		}
	}
}