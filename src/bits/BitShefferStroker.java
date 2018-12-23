package bits;

/**
 * An extension of the Problem class which imposes a Boolean relation on two
 * IBooleanVariables. For example, the Problem instance p defined by
 *
 * <p>
 * <code>Problem p=new BitShefferStroker(x,y);</code>
 * </p>
 *
 * is satisfied if and only if the following Boolean relation is satisfied:
 *
 * <p>
 * <code>!x | !y</code>
 * </p>
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2015/03/18
 */
public class BitShefferStroker extends Problem implements IProblem
{
	public BitShefferStroker(IBooleanVariable x, IBooleanVariable y)
			throws Exception
	{
		this.setClauses(new IClause[]
		{ Clause.newClause().orNot(x).orNot(y) });
	}
}