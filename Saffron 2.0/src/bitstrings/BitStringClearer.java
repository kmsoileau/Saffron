package bitstrings;

import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import exceptions.bitstrings.BitStringClearerException;

/**
 * Constrains the <code>IBooleanVariable</code> at position <code>bit</code> of
 * <code>IBitString b</code> to equal the boolean value <code>false</code>.
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2005/04/15
 */
public class BitStringClearer extends Problem implements IProblem
{
	public BitStringClearer(IBitString X, IBitString Y) throws Exception
	{
		if (X.size() != Y.size())
			throw new BitStringClearerException(
					"X and Y are not of equal size.");
		// this.setClauses(Problem.unsolvableProblem().getClauses());
		else
		{
			int commonsize = X.size();
			IProblem[] p = new IProblem[commonsize];
			for (int i = 0; i < commonsize; i++)
				p[i] = new BitStringBitFixer(Y, i, false);
			this.setClauses(new Conjunction(p).getClauses());
		}
	}
}