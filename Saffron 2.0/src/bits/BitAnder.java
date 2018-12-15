package bits;

/**
 * An extension of the <code>Problem</code> class which imposes a Boolean
 * relation on three <code>IBooleanVariable</code>s. For example, the
 * <code>IProblem p</code> defined by
 *
 * <p>
 * <code>IProblem p=new BitAnder(x,y,z);</code>
 * </p>
 *
 * is satisfied if and only if the following Boolean relation is satisfied:
 *
 * <p>
 * <code>z == x &amp; y </code>
 * </p>
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.2
 * @since 2005/02/09
 */
public class BitAnder extends Problem implements IProblem
{
	public BitAnder(IBooleanVariable x, IBooleanVariable y, IBooleanVariable z)
			throws Exception
	{
		this.setClauses(new IClause[]
		{ Clause.newClause().or(x).orNot(z), Clause.newClause().or(y).orNot(z),
				Clause.newClause().orNot(x).orNot(y).or(z) });
	}
}
