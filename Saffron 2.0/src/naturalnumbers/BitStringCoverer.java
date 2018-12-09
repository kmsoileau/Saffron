package naturalnumbers;

import bits.Conjunction;
import bits.IBitString;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import bitstringlists.IBitStringList;
import bitstrings.BitString;
import bitstrings.BitStringFixer;
import bitstrings.ConditionalOrer;
import exceptions.naturalnumbers.BitStringCovererException;

/*
 * Given a collection C of BitStrings each of size n and a positive integer 
 * K, does there exist a subset of C of size K such that the bitwise OR of 
 * the members of the subset evaluate to the BitString 111...1 of size n?
 */
public class BitStringCoverer extends Problem implements IProblem
{
	public BitStringCoverer(IBitStringList C, IBitString included,
			INaturalNumber K) throws Exception
	{
		if(C==null)
			throw new BitStringCovererException("Null passed to constructor as IBitStringList.");
		if(included==null)
			throw new BitStringCovererException("Null passed to constructor as IBitString.");
		if(K==null)
			throw new BitStringCovererException("Null passed to constructor as INaturalNumber.");
		if(C.size()==0)
			throw new BitStringCovererException("IBitStringList of zero length passed to constructor.");
		int len = C.getBitString(0).size();
		IBitString conditionalResult = new BitString(len);
		for (int i = 0; i < len; i++)
			conditionalResult.getBooleanVariable(i).setValue(true);

		this.setClauses(new Conjunction(new ConditionalOrer(C, included, conditionalResult), 
				new BitStringFixer(conditionalResult), new BitStringTotaler(included, K)).getClauses());
	}
}
