package bits.strings;

import bits.BitFixer;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import bits.strings.exceptions.BitStringBitFixerException;

/**
 * Constrains the <code>IBooleanVariable</code> at position <code>bit</code> of
 * <code>IBitString b</code> to equal the boolean value <code>val</code>.
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2005/04/15
 */
public class BitStringBitFixer extends Problem implements IProblem
{
	public BitStringBitFixer(IBitString b, int bit, boolean val)
			throws Exception
	{
		if ((bit < 0) || (b.size() - 1 < bit))
			throw new BitStringBitFixerException(
					"bit < 0 or b.size() - 1 < bit.");
		else
			this.setClauses(new BitFixer(b.getBooleanVariable(bit), val)
					.getClauses());
	}
}