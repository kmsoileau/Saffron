package bitstringlists;

import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import bitstrings.BitStringSubstringer;
import exceptions.bitstringlists.CommonSuperBitStringException;

/*
 * Given a collection C of IBitStrings each of size n, and an IBitString 
 * targetBitString of size K>=n, does there exist an IBitString of length 
 * K such that every member of C is a substring of targetBitString?
 */
public class CommonSuperBitString extends Problem implements IProblem
{
	public CommonSuperBitString(IBitString[] C, IBitString superSequence)
			throws Exception
	{
		int len = C.length;
		int sssize = superSequence.size();
		for (int i = 0; i < len; i++)
			if (C[i].size() > sssize)
				throw new CommonSuperBitStringException(
						"The size of the IBitSring is maller than at least one of the IBitStrings in the IBitStringList.");

		IProblem[] p = new IProblem[len];
		for (int i = 0; i < len; i++)
			p[i] = new BitStringSubstringer(C[i], superSequence);

		this.setClauses(new Conjunction(p).getClauses());
	}

	public CommonSuperBitString(IBitStringList C, IBitString superSequence)
			throws Exception
	{
		int size = C.size();
		int sssize = superSequence.size();
		for (int i = 0; i < size; i++)
			if (C.getBitString(i).size() > sssize)
				throw new CommonSuperBitStringException(
						"The size of the IBitSring is maller than at least one of the IBitStrings in the IBitStringList.");

		IProblem[] p = new IProblem[size];
		for (int i = 0; i < size; i++)
			p[i] = new BitStringSubstringer(C.getBitString(i), superSequence);

		this.setClauses(new Conjunction(p).getClauses());
	}
}
