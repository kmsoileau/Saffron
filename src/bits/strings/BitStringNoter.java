package bits.strings;

import bits.BitNoter;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import bits.strings.exceptions.BitStringNoterException;

/**
 * For each position <code>bit</code> in <code>IBitString X</code>, the
 * corresponding <code>IBooleanVariable</code> is constrained to equal the
 * negation of its counterpart in <code>IBitString Y</code>.
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
		if (X == null || Y == null)
			throw new BitStringNoterException("Null was passed to constructor for IBitString.");
		IProblem[] p = new IProblem[X.size()];
		int count = 0;
		for (int i = 0; i < p.length; i++)
			p[count++] = new BitNoter(X.getBooleanVariable(i), Y.getBooleanVariable(i));
		this.setClauses(new Conjunction(p).getClauses());
	}
}