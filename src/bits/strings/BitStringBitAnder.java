package bits.strings;

import bits.BitEqualizer;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;

/**
 * Given a collection <code>C</code> of <code>IBitString</code>s of equal size,
 * and a bit position <code>pos</code>, constrain <code>targetBitString</code>
 * to be the <code>AND</code> of the members of <code>C</code> for which bit
 * position <code>pos</code> is <code>true</code>.
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2018/12/15
 */
public class BitStringBitAnder extends Problem implements IProblem
{
	public BitStringBitAnder(IBitString[] C, int pos, IBitString targetBitString) throws Exception
	{
		IBitString membership = new BitString(C.length);

		IProblem[] p = new IProblem[C.length];
		for (int i = 0; i < C.length; i++)
			p[i] = new BitEqualizer(membership.getBooleanVariable(i), C[i].getBooleanVariable(pos));

		this.setClauses(new Conjunction(new BitStringFixer(C), new Conjunction(p),
				new BitStringConditionalAnder(C, membership, targetBitString)).getClauses());
	}

}
