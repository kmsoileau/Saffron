/*
 * BitStringDominatora	1.0 2018/12/04
 *
 * Copyright 2018 Positronic Software.
 *
 *
 */

package sets;

import bits.BitFixer;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;

/**
 * Satisfied when X[i]==true and Y[i]==true for some i.
 */
public class SetMeeter extends Problem implements IProblem
{
	public SetMeeter(Set X, Set Y) throws Exception
	{
		int commonsize = Set.getSetSupportSize();
		IProblem[] c = new IProblem[commonsize];

		for (int i = 0; i < commonsize; i++)
		{
			IBooleanVariable currX = X.getBooleanVariable(i);
			IBooleanVariable currY = Y.getBooleanVariable(i);

			c[i] = new Conjunction(new BitFixer(currX, true), new BitFixer(currY, true));
		}

		this.setClauses(new Disjunction(c).getClauses());
	}
}