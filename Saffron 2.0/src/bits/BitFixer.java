package bits;

/**
 * An extension of the Problem class which implements a Boolean constraint. This
 * constraint is satisfied if and only if the IBooleanVariable x has a given
 * truth value.
 *
 * For example, when the Problem instance p defined by
 *
 * <p>
 * <tt>Problem p=new BitFixer(x,true);</code>
 * </p>
 *
 * is satisfied, the following truth equation will be satisfied:
 *
 * <p>
 * <tt>x==true</code>
 * </p>
 * 
 * Similarly, when the Problem instance p defined by
 *
 * Problem p=new BitFixer(x,false);
 *
 * is satisfied, the following truth equation will be satisfied:
 *
 * <p>
 * <tt>x==false</code>
 * </p>
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.1
 * @since 2004/10/05
 */
public class BitFixer extends Problem implements IProblem
{
	public BitFixer(IBooleanVariable x) throws Exception
	{
		this(x, x.getValue());
	}

	public BitFixer(IBooleanVariable x, boolean v) throws Exception
	{
		this.addClause(Clause.newClause());

		if (v)
			this.getClause(0).or(x);
		else
			this.getClause(0).orNot(x);
	}
}