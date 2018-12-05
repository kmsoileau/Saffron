package bitstrings;

import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import bits.ProblemDenier;
import exceptions.bitstrings.BitStringDominatorException;

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
public class BitStringNonDominator extends Problem implements IProblem
{
	public BitStringNonDominator(IBitString X, IBitString Y) throws Exception
	{
		if (X.size() != Y.size())
			throw new BitStringDominatorException(
					"X and Y are not of equal size.");
		else
		{
			IProblem prob = new BitStringDominator(X, Y);
			IProblem nonprob = new ProblemDenier(prob);
			this.setClauses(nonprob.getClauses());
		}
	}
}