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

package bitstringlists;

import bits.Conjunction;
import bits.IProblem;
import bits.Problem;
import bitstrings.BitStringEqualizer;

public class BitStringListReverser extends Problem implements IProblem
{
	private static final long serialVersionUID = -3186500319524626108L;

	public BitStringListReverser(IBitStringList X, IBitStringList Y)
			throws Exception
	{
		if (X.size() != Y.size())
			this.setClauses(Problem.unsolvableProblem().getClauses());
		else
		{
			int commonsize = X.size();
			IProblem[] p = new IProblem[commonsize];
			int count = 0;
			for (int i = 0; i < commonsize; i++)
				p[count++] = new BitStringEqualizer(X.getBitString(i),
						Y.getBitString(commonsize - 1 - i));

			this.setClauses(new Conjunction(p).getClauses());
		}
	}
}