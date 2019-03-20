package bitstrings;

import bits.BitFixer;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBitString;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;
import exceptions.bitstrings.BitStringNonDominatorException;

/**
 * Satisfied when X is not dominated by Y.
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2018/12/04
 */
/*
 * BitStringDominatora 1.0 2018/12/04
 * 
 * Copyright 2018 Positronic Software.
 */

/*
 * Satisfied when X[i]==true and Y[i]==false for some i.
 */
public class BitStringNonDominator extends Problem implements IProblem
{
	public BitStringNonDominator(IBitString X, IBitString Y) throws Exception
	{
		if (X.size() != Y.size())
			throw new BitStringNonDominatorException(
					"X and Y are not of equal size.");
		else
		{
			int commonsize = X.size();
			IProblem[] c = new IProblem[commonsize];
			for (int i = 0; i < X.size(); i++)
			{
				IBooleanVariable currX = X.getBooleanVariable(i);
				IBooleanVariable currY = Y.getBooleanVariable(i);
				c[i] = new Conjunction(new BitFixer(currX, true), new BitFixer(
						currY, false));
			}

			this.setClauses(new Disjunction(c).getClauses());
		}
	}
}