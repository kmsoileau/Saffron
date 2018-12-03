package bits;

import exceptions.bits.BitEqualizerException;

/**
 * An extension of the Problem class which imposes a Boolean relation on two
 * IBooleanVariables. For example, the Problem instance p defined by
 *
 * <p>
 * <tt>Problem p=new BitEqualizer(x,y);</code>
 * </p>
 *
 * is satisfied if and only if the following Boolean relation is satisfied:
 *
 * <p>
 * <tt>x==y</code>
 * </p>
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.11
 * @since 2005/11/30
 */
public class BitEqualizer extends Problem implements IProblem
{
	public BitEqualizer(IBooleanVariable x, IBooleanVariable y)
			throws Exception
	{
		if (x == null || y == null)
			throw new BitEqualizerException(
					"BitEqualizer constructor was passed a null parameter.");

		if (x.equals(y))
			this.setClauses(Problem.trivialProblem().getClauses());
		else
			this.setClauses(new IClause[]
			{ Clause.newClause().or(x).orNot(y),
					Clause.newClause().orNot(x).or(y) });
	}
}