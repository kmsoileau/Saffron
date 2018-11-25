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

package naturalnumberlists;

import naturalnumbers.NaturalNumberEqualizer;
import bits.Disjunction;
import bits.IBitString;
import bits.IClause;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import bitstrings.BitStringEqualizer;

@SuppressWarnings("unused")
public class NaturalNumberListRepeater extends Problem implements IProblem
{
	public NaturalNumberListRepeater(INaturalNumberList list) throws Exception
	{
		IProblem p = null;
		for (int i = 0; i < list.size(); i++)
		{
			INaturalNumber b = list.getNaturalNumber(i);
			for (int j = i + 1; j < list.size(); j++)
				p = new Disjunction(p, new NaturalNumberEqualizer(b,
						list.getNaturalNumber(j)));
		}
		if (p == null)
			this.setClauses((IClause[]) null);
		else
			this.setClauses(p.getClauses());
	}
}
