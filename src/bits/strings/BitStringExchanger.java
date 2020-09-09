package bits.strings;

import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;

/**
 * Constrains <code>xAfter==yBefore</code> and <code>yAfter==xBefore</code>.
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2005/04/15
 */
public class BitStringExchanger extends Problem implements IProblem
{
	public BitStringExchanger(IBitString xBefore, IBitString yBefore, IBitString xAfter, IBitString yAfter)
			throws Exception
	{
		IProblem p = new Conjunction(new BitStringEqualizer(xBefore, yAfter), new BitStringEqualizer(yBefore, xAfter));
		this.setClauses(p.getClauses());
	}
}
