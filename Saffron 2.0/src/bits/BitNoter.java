package bits;

/**
 * An extension of the Problem class which imposes a Boolean relation on two
 * IBooleanVariables. For example, the Problem instance p defined by
 *
 * <p>
 * <tt>Problem p=new BitNoter(x,y);</code>
 * </p>
 *
 * is satisfied if and only if the following Boolean relation is satisfied:
 *
 * <p>
 * <tt>y == !x</code>
 * </p>
 *
 * Note that BitNoter is equivalent to BitUnequalizer.
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.1
 * @since 2004/10/05
 */
public class BitNoter extends Problem implements IProblem
{
	public BitNoter(IBooleanVariable x, IBooleanVariable y) throws Exception
	{
		this.setClauses(new IClause[]
		{ Clause.newClause().or(x).or(y), Clause.newClause().orNot(x).orNot(y) });
	}
}