package bits;

import java.util.ArrayList;

import bits.exceptions.ClauseDenierException;
import positronic.util.arrays.CompoundList;
import positronic.util.arrays.ImmutableList;

/**
 * An extension of the Problem class which expresses the denial of a given
 * IClause. More specifically, the IProblem p defined by
 *
 * <p>
 * <code>IProblem p=new ClauseDenier(clause);</code>
 * </p>
 *
 * is satisfied if and only if the IClause clause is not satisfied.
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Oct 18, 2005
 */
public class ClauseDenier extends Problem implements IProblem
{
	public ClauseDenier(IClause clause) throws Exception
	{
		// System.out.println("Current clause is "+clause);
		if (clause == null)
			throw new ClauseDenierException("Null clause passed to constructor.");
		else
		{
			if (clause.size() == 0)
			{
				this.setBacking(new CompoundList(EnhancedProblem.trivialProblem().getClauses()));
			}
			else
			{
				ArrayList<IClause> ret = new ArrayList<IClause>();
				for (IBooleanLiteral ib : clause.toArray())
				{
					IBooleanVariable curr = ib.getBooleanVariable();
					IClause cl;
					if (ib.isBarred())
					{
						cl = Clause.newClause().or(curr);
					}
					else
					{
						cl = Clause.newClause().orNot(curr);
					}
					ret.add(cl);
				}
				ImmutableList cl = new CompoundList(ret.toArray());
				//System.out.println(cl);
				this.setBacking(cl);
			}
		}
	}
}