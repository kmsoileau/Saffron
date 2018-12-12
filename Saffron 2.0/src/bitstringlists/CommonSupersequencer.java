package bitstringlists;

import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import bitstrings.BitStringSubstringer;

/*
 * Given a collection C of IBitStrings each of size n, and an IBitString 
 * targetBitString of size K>=n, does there exist an IBitString of length 
 * K such that every member of C is a substring of targetBitString?
 */
public class CommonSupersequencer extends Problem implements IProblem
{

	public CommonSupersequencer(IBitStringList C, IBitString superSequence)
			throws Exception
	{
		IProblem[] p = new IProblem[C.size()];
		for (int i = 0; i < C.size(); i++)
			p[i] = new BitStringSubstringer(C.getBitString(i), superSequence);

		this.setClauses(new Conjunction(p).getClauses());
	}
}
