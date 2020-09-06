package bits.strings.lists;

import bits.Conjunction;
import bits.IBitString;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.BitStringArrayCoverer;
import bits.strings.exceptions.BitStringCovererException;
import naturalnumbers.BitStringTotaler;

/*
 * Given a collection C of BitStrings each of size n and a positive integer 
 * K, does there exist a subset of C of size K such that the bitwise OR of 
 * the members of the subset evaluate to the BitString 111...1 of size n?
 */
public class BitStringSizedCoverer extends Problem implements IProblem
{
	public BitStringSizedCoverer(IBitStringList C, IBitString included,
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
		if (C.size() == 0)
			throw new BitStringCovererException(
					"IBitStringList of zero size passed to constructor.");
		int len = C.getBitString(0).size();
		IBitString conditionalResult = new BitString(len);
		for (int i = 0; i < len; i++)
			conditionalResult.getBooleanVariable(i).setValue(true);

		this.setClauses(new Conjunction(new BitStringArrayCoverer(C, included),
				new BitStringTotaler(included, K)).getClauses());
	}

}
