package bits;

/**
 * An extension of the Problem class which imposes a Boolean relation on two
 * IBooleanVariables. For example, the Problem instance p defined by
 *
 * <p>
 * <tt>Problem p=new BitOrderer(x,y);</code>
 * </p>
 *
 * is satisfied if and only if the following Boolean relation is satisfied:
 *
 * <p>
 * <tt>x implies y</code>
 * </p>
 *
 * Equivalently,
 * <p>
 * <tt>Problem p=new BitOrderer(x,y);</code>
 * </p>
 *
 * is satisfied if and only if the following Boolean relation is satisfied:
 *
 * <p>
 * <tt>y==true or (y==false && x==false)</code>
 * </p>
 * .
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2005/05/04
 */
public class BitOrderer extends Problem implements IProblem
{
	public BitOrderer(IBooleanVariable x, IBooleanVariable y) throws Exception
	{
		IProblem compare = new Disjunction(new BitFixer(y, true),
				new Conjunction(new BitFixer(y, false), new BitFixer(x, false)));
		this.setClauses(compare.getClauses());
	}
}