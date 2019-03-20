/*
 * BitStringDominatora	1.0 2018/12/04
 *
 * Copyright 2018 Positronic Software.
 *
 *
 */

package sets;

import bits.Clause;
import bits.IBooleanVariable;
import bits.IClause;
import bits.IProblem;
import bits.Problem;

/*
 * Satisfied when either X[i]==false or Y[i]==false for every i.
 */
public class SetDisjointer extends Problem implements IProblem
{
	public SetDisjointer(Set X, Set Y) throws Exception
	{
		int commonsize = Set.getSetSupportSize();
		IClause[] c = new IClause[commonsize];

		for (int i = 0; i < commonsize; i++)
		{
			IBooleanVariable currX = X.getBooleanVariable(i);
			IBooleanVariable currY = Y.getBooleanVariable(i);
			c[i] = Clause.newClause().orNot(currX).orNot(currY);
		}

		this.setClauses(c);
	}
}