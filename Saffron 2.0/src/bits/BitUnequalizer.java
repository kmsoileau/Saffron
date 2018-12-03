package bits;

import exceptions.bits.BitUnequalizerException;

/**
 * An extension of the Problem class which imposes a Boolean relation on two
 * IBooleanVariables. For example, the Problem instance p defined by
 *
 * <p>
 * <tt>Problem p=new BitUnequalizer(x,y);</code>
 * </p>
 *
 * is satisfied if and only if the following Boolean relation is satisfied:
 *
 * <p>
 * <tt>x!=y</code>
 * </p>
 *
 * Note that BitUnequalizer is equivalent to BitNoter.
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.1
 * @since 2005/03/09
 */
public class BitUnequalizer extends Problem implements IProblem
{
	public BitUnequalizer(IBooleanVariable x, IBooleanVariable y)
			throws Exception
	{
		if (x == null || y == null)
			throw new BitUnequalizerException(
					"Null IBooleanVariable was passed to constructor.");
		this.setClauses(new IClause[]
		{ Clause.newClause().or(x).or(y), Clause.newClause().orNot(x).orNot(y) });
	}
}