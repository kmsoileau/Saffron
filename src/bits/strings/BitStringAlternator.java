package bits.strings;

import bits.BitUnequalizer;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;

/**
 * Constrains a BitString so that adjacent bits are negations of each other. To
 * put it another way, the BitString will consist of alternating logical values.
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.2
 * @since 2005/10/27
 */
public class BitStringAlternator extends Problem implements IProblem
{
	public BitStringAlternator(IBitString X) throws Exception
	{
		IProblem[] p = new IProblem[X.size()];
		for (int i = 0; i < X.size() - 1; i++)
			p[i] = new BitUnequalizer(X.getBooleanVariable(i),
					X.getBooleanVariable(i + 1));

		this.setClauses(new Conjunction(p).getClauses());
	}
}