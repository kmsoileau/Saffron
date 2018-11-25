package bitstrings;

import bits.BitAnder;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import bitstrings.exceptions.BitStringAnderException;

/**
 * Constrains the IBitString Z to be the bitwise AND of IBitString X and
 * IBitString Y.
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2005/04/15
 */
public class BitStringAnder extends Problem implements IProblem
{
	public BitStringAnder(IBitString X, IBitString Y, IBitString Z)
			throws Exception
	{
		if ((X.size() != Y.size()) || (X.size() != Z.size()))
			throw new BitStringAnderException(
					"X, Y and Z are not of equal size.");
		else
		{
			int commonsize = X.size();
			IProblem[] p = new IProblem[commonsize];
			int count = 0;
			for (int i = 0; i < commonsize; i++)
				p[count++] = new BitAnder(X.getBooleanVariable(i),
						Y.getBooleanVariable(i), Z.getBooleanVariable(i));
			this.setClauses(new Conjunction(p).getClauses());
		}
	}
}