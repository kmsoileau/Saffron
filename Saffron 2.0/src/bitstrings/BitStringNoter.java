package bitstrings;

import bits.BitNoter;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;

/**
 * Constrains the <code>IBooleanVariable</code> at position <code>bit</code> of
 * <code>IBitString Y</code> to equal to the negation of the
 * <code>IBooleanVariable</code> at position <code>bit</code> of
 * <code>IBitString X</code>
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2005/04/01
 */
public class BitStringNoter extends Problem implements IProblem
{
	public BitStringNoter(IBitString X, IBitString Y) throws Exception
	{
		IProblem[] p = new IProblem[X.size()];
		int count = 0;
		for (int i = 0; i < p.length; i++)
			p[count++] = new BitNoter(X.getBooleanVariable(i),
					Y.getBooleanVariable(i));
		this.setClauses(new Conjunction(p).getClauses());
	}
}