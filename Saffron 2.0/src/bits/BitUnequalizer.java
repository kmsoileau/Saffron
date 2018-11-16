/*
 * BitUnequalizer.java	1.1 05/03/09
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */
/**
 An extension of the Problem class which imposes a Boolean relation on two
 * IBooleanVariables. For example, the Problem instance p defined by
 *
 * <p><tt>Problem p=new BitUnequalizer(x,y);</tt></p>
 *
 * is satisfied if and only if the following Boolean relation is satisfied:
 *
 * <p><tt>x!=y</tt></p>
 *
 * Note that BitUnequalizer is equivalent to BitNoter.
 *
 * @author  Kerry Michael Soileau
 * ksoileau2@yahoo.com
 * http://kerrysoileau.com/index.html
 * @version 1.1, 05/03/09
 * @see IBooleanVariable
 * @see IClause
 * @see Problem
 */

package bits;

public class BitUnequalizer extends Problem implements IProblem
{
	public BitUnequalizer(IBooleanVariable x, IBooleanVariable y)
			throws Exception
	{
		if (x == null || y == null)
			throw new BitUnequalizerException(
					"Null IBooleanVariable was passed to constructor.");
		this.setClauses(new IClause[]
		{ Clause.newClause().or(x).or(y),
				Clause.newClause().orNot(x).orNot(y) });
	}
}