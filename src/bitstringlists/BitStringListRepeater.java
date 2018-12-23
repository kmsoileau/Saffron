/*
 * BitStringListRepeater.java	1.0 06/02/10
 *
 * Copyright 2006 Positronic Software.
 *
 *
 */

/**
 * A class which imposes the constraint that at least one member of
 * of the given IBitStringList appears at least twice in that 
 * IBitStringList.
 *
 * @author  Kerry Michael Soileau
 * <blockquote><pre>
 * ksoileau2@yahoo.com
 * http://kerrysoileau.com/index.html
 * </pre></blockquote>
 * @version 1.0, 06/02/10
 * @see BitStringEqualizer
 * @see IBitString
 * @see Disjunction
 * @see IClause
 * @see IProblem
 * @see Problem
 */

package bitstringlists;

import bits.Disjunction;
import bits.IBitString;
import bits.IClause;
import bits.IProblem;
import bits.Problem;
import bitstrings.BitStringEqualizer;

public class BitStringListRepeater extends Problem implements IProblem
{
	public BitStringListRepeater(IBitStringList list) throws Exception
	{
		IProblem p = null;
		for (int i = 0; i < list.size(); i++)
		{
			IBitString b = list.getBitString(i);
			for (int j = i + 1; j < list.size(); j++)
				p = new Disjunction(p, new BitStringEqualizer(b,
						list.getBitString(j)));
		}
		if (p == null)
			this.setClauses((IClause[]) null);
		else
			this.setClauses(p.getClauses());
	}
}
