package bits.strings;

import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import bits.strings.exceptions.BitStringClearerException;

/**
 * Constrains all <code>IBooleanVariable</code>s of <code>IBitString b</code> to
 * equal the boolean value <code>false</code>.
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
	public BitStringClearer(IBitString X) throws Exception
	{
		if (X == null)
			throw new BitStringClearerException("A null IBitString was passed to a constructor.");
		else
		{
			int commonsize = X.size();
			IProblem[] p = new IProblem[commonsize];
			for (int i = 0; i < commonsize; i++)
				p[i] = new BitStringBitFixer(X, i, false);
			this.setClauses(new Conjunction(p).getClauses());
		}
	}
}