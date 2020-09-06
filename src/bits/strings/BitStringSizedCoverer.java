/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Jan 20, 2019
 */
package bits.strings;

import bits.Conjunction;
import bits.IBitString;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import bits.strings.exceptions.BitStringCovererException;
import naturalnumbers.BitStringTotaler;

/**
 * 
 *
 */
public class BitStringSizedCoverer extends Problem implements IProblem
{
	public BitStringSizedCoverer(IBitString[] C, IBitString included,
			INaturalNumber K) throws Exception
	{
		if (C == null)
			throw new BitStringCovererException(
					"Null passed to constructor as IBitStringList.");
		if (included == null)
			throw new BitStringCovererException(
					"Null passed to constructor as IBitString.");
		if (K == null)
			throw new BitStringCovererException(
					"Null passed to constructor as INaturalNumber.");
		if (C.length == 0)
			throw new BitStringCovererException(
					"IBitStringList of zero size passed to constructor.");
		int len = C[0].size();
		IBitString conditionalResult = new BitString(len);
		for (int i = 0; i < len; i++)
			conditionalResult.getBooleanVariable(i).setValue(true);

		this.setClauses(new Conjunction(new BitStringArrayCoverer(C, included),
				new BitStringTotaler(included, K)).getClauses());
	}
}
