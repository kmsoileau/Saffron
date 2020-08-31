package bits;

/**
 * An extension of the Problem class which imposes a Boolean relation on three
 * IBooleanVariables. For example, the Problem instance p defined by
 *
 * <p>
 * <code>Problem p=new BitInequalityIndicator(x,y,z);</code>
 * </p>
 *
 * is satisfied if and only if the following Boolean relation is satisfied:
 *
 * <p>
 * <code>z == (x!=y)</code>
 * </p>
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.1
 * @since Oct 5, 2004
 */
public class BitInequalityIndicator extends Problem implements IProblem
{
	public BitInequalityIndicator(IBooleanVariable x, IBooleanVariable y,
			IBooleanVariable z) throws Exception
	{
		this.setClauses(new IClause[]
		{ Clause.newClause().or(x).or(y).orNot(z),
				Clause.newClause().or(x).orNot(y).or(z),
				Clause.newClause().orNot(x).or(y).or(z),
				Clause.newClause().orNot(x).orNot(y).orNot(z) });
	}
}
