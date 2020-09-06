package bits;

/**
 * An extension of the Problem class which imposes a Boolean relation on two
 * IBooleanVariables. For example, the Problem instance p defined by
 *
 * <p>
 * <code>Problem p=new BitSubtractor(x,y,z);</code>
 * </p>
 *
 * is satisfied if and only if the following Boolean relation is satisfied:
 *
 * <p>
 * <code>z == x &amp; !y</code>
 * </p>
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 5, 20194
 */
public class BitSubtractor extends Problem implements IProblem
{
	public BitSubtractor(IBooleanVariable x, IBooleanVariable y,
			IBooleanVariable z) throws Exception
	{
		this.setClauses(new IClause[]
				{ Clause.newClause().or(x).orNot(z), Clause.newClause().orNot(y).orNot(z),
						Clause.newClause().orNot(x).or(y).or(z) });
	}
}