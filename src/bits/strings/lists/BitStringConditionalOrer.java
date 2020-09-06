package bits.strings.lists;

import bits.IBitString;
import bits.IProblem;
import bits.Problem;

/**
 * Given a collection C of IBitStrings each of size n, and an IBitString
 * targetBitString of size n, does there exist a subset of C such that the
 * bitwise OR of the members of the subset evaluate to targetBitString?
 */
public class BitStringConditionalOrer extends Problem implements IProblem
{
	public BitStringConditionalOrer(IBitStringList bitStringList,
			IBitString membership, IBitString conditionalResult)
			throws Exception
	{
		this.setClauses(new bits.strings.BitStringConditionalOrer(bitStringList
				.toList().toArray(new IBitString[0]), membership,
				conditionalResult).getClauses());
	}
}