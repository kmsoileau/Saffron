/*
 * ClauseDenier.java	1.0 05/10/18
 *
 * Copyright 2005 Positronic Software.
 *
 *
 */

package bits;

/**
 * An extension of the Problem class which expresses the denial of a given
 * IClause. More specifically, the IProblem p defined by
 *
 * <p>
 * <tt>IProblem p=new ClauseDenier(clause);</tt>
 * </p>
 *
 * is satisfied if and only if the IClause clause is not satisfied.
 *
 * @author Kerry Michael Soileau ksoileau2@yahoo.com
 *         http://kerrysoileau.com/index.html
 * @version 1.0, 05/10/18
 * @see BooleanLiteralException
 * @see IBooleanLiteral
 * @see IClause
 * @see IProblem
 * @see Problem
 */
public class ClauseDenier extends Problem implements IProblem
{
	public ClauseDenier(IClause clause) throws Exception
	{
		if (clause == null)
			throw new ClauseDenierException("Null clause passed to constructor.");
			//this.setClauses(Problem.unsolvableProblem().getClauses());
		else
		{
			Object[] oib = clause.toArray();
			for (int i = 0; i < oib.length; i++)
			{
				IBooleanLiteral ib = (IBooleanLiteral) (oib[i]);
				if (ib.isBarred())
					super.addClause(
							Clause.newClause().or(ib.getBooleanVariable()));
				else
					super.addClause(
							Clause.newClause().orNot(ib.getBooleanVariable()));
			}
		}
	}
}