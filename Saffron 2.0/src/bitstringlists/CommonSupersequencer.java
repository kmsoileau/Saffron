package bitstringlists;

import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import bitstrings.BitStringSubstringer;
import exceptions.bitstringlists.CommonSupersequencerException;

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
		for (int i = 0; i < C.size(); i++)
			if (C.getBitString(i).size() > superSequence.size())
				throw new CommonSupersequencerException(
						"The size of the IBitSring is maller than at least one of the IBitStrings in the IBitStringList.");

		IProblem[] p = new IProblem[C.size()];
		for (int i = 0; i < C.size(); i++)
			p[i] = new BitStringSubstringer(C.getBitString(i), superSequence);

		this.setClauses(new Conjunction(p).getClauses());
	}
}
