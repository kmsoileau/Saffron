package bits;

import exceptions.bits.ClauseDenierException;

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
 * @since 2005/10/18
 */
public class ClauseDenier extends Problem implements IProblem
{
	public ClauseDenier(IClause clause) throws Exception
	{
		if (clause == null)
			throw new ClauseDenierException(
					"Null clause passed to constructor.");
		// this.setClauses(Problem.unsolvableProblem().getClauses());
		else
		{
			if(clause.size()==0)
			{
				this.setClauses(Problem.trivialProblem().getClauses());
			}
			else
			{
				Object[] oib = clause.toArray();
				for (int i = 0; i < oib.length; i++)
				{
					IBooleanLiteral ib = (IBooleanLiteral) (oib[i]);
					if (ib.isBarred())
						super.addClause(Clause.newClause().or(
								ib.getBooleanVariable()));
					else
						super.addClause(Clause.newClause().orNot(
								ib.getBooleanVariable()));
				}
			}
		}
	}
}