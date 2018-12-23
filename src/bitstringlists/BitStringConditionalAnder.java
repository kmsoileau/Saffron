package bitstringlists;

import bits.IBitString;
import bits.IProblem;
import bits.Problem;

/**
 * Given an IBitStringList C of IBitStrings each of size n, and an IBitString
 * targetBitString of size n, does there exist a sublist of C such that the
 * bitwise AND of the members of the sublist evaluate to targetBitString?
 */
public class BitStringConditionalAnder extends Problem implements IProblem
{
	public BitStringConditionalAnder(IBitStringList bitStringList,
			IBitString membership, IBitString conditionalResult)
			throws Exception
	{
		this.setClauses(new bitstrings.BitStringConditionalAnder(bitStringList
				.toList().toArray(new IBitString[0]), membership,
				conditionalResult).getClauses());
	}
}