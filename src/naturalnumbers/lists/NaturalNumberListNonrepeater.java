/*
 * NaturalNumberListNonrepeater.java	1.0 06/04/16
 *
 * Copyright 2006 Positronic Software.
 *
 *
 */

/**
 * A class which imposes the restriction of being a Set on a NaturalNumberList. 
 * That is, that the NaturalNumberList has no repetition of members; each member 
 * appears only once in the NaturalNumberList.
 *
 * @author  Kerry Michael Soileau
 * <blockquote><pre>
 * ksoileau2@yahoo.com
 * http://kerrysoileau.com/index.html
 * </pre></blockquote>
 * @version 1.0, 06/04/16
 * @see NaturalNumberUnequalizer
 * @see INaturalNumber
 * @see Conjunction
 * @see IClause
 * @see IProblem
 * @see Problem
 */

package naturalnumbers.lists;

import bits.Conjunction;
import bits.IClause;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.NaturalNumberUnequalizer;

public class NaturalNumberListNonrepeater extends Problem implements IProblem
{
	public NaturalNumberListNonrepeater(INaturalNumberList list)
			throws Exception
	{
		IProblem p = null;
		for (int i = 0; i < list.size(); i++)
		{
			INaturalNumber b = list.getNaturalNumber(i);
			for (int j = i + 1; j < list.size(); j++)
				p = new Conjunction(p, new NaturalNumberUnequalizer(b,
						list.getNaturalNumber(j)));
		}
		if (p == null)
			this.setClauses((IClause[]) null);
		else
			this.setClauses(p.getClauses());
	}
}
