package bits.strings;

import bits.BitEqualizer;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import bits.strings.exceptions.BitStringHighPopperException;

/**
 * An extension of the <code>Problem</code> class which constrains parameter
 * <code>Y</code> to equal the <code>IBitString</code> obtained when the bit of
 * highest index in <code>X</code> is dropped. Naturally, the size of
 * <code>Y</code> must be one less than the size of <code>X</code>.
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2005/04/21
 */
public class BitStringHighPopper extends Problem implements IProblem
{
	public BitStringHighPopper(IBitString X, IBitString Y) throws Exception
	{
		if (X.size() != Y.size() + 1)
			throw new BitStringHighPopperException("X.size() != Y.size() + 1");
		else
		{
			IProblem[] p = new IProblem[X.size()];
			int count = 0;
			for (int i = 0; i < Y.size(); i++)
				p[count++] = new BitEqualizer(X.getBooleanVariable(i),
						Y.getBooleanVariable(i));
			this.setClauses(new Conjunction(p).getClauses());
		}
	}
}