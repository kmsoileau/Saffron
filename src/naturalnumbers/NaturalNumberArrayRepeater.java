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

package naturalnumbers;

import bits.Disjunction;
import bits.IClause;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

@SuppressWarnings("unused")
public class NaturalNumberArrayRepeater extends Problem implements IProblem
{
	public NaturalNumberArrayRepeater(INaturalNumber[] array) throws Exception
	{
		IProblem p = null;
		for (int i = 0; i < array.length; i++)
		{
			INaturalNumber b = array[i];
			for (int j = i + 1; j < array.length; j++)
				p = new Disjunction(p, new NaturalNumberEqualizer(b, array[j]));
		}
		if (p == null)
			this.setClauses((IClause[]) null);
		else
			this.setClauses(p.getClauses());
	}
}
