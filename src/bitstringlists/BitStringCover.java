package bitstringlists;

import bits.BitFixer;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import bitstrings.BitStringFixer;
import exceptions.bitstringlists.BitStringCoverException;

/*
 * Given a collection C of BitStrings each of size n and a positive integer 
 * K, does there exist a subset of C of size K such that the bitwise OR of 
 * the members of the subset evaluate to the BitString 111...1 of size n?
 */
public class BitStringCover extends Problem implements IProblem
{
	public BitStringCover(IBitString[] C, IBitString membership)
			throws Exception
	{
		if (C == null)
			throw new BitStringCoverException(
					"Null passed to constructor as IBitStringList.");
		if (membership == null)
			throw new BitStringCoverException(
					"Null passed to constructor as IBitString.");
		int cLength = C.length;
		if (C.length == 0)
			throw new BitStringCoverException(
					"IBitString array of zero length passed to constructor.");

		IProblem[] q = new IProblem[C[0].size()];
		for (int i = 0; i < C[0].size(); i++)
		{
			IProblem[] p = new IProblem[cLength];
			for (int j = 0; j < cLength; j++)
			{
				p[j] = new Conjunction(new BitFixer(
						membership.getBooleanVariable(j), true), new BitFixer(
						C[j].getBooleanVariable(i), true));
			}
			q[i] = new Disjunction(p);
		}

		this.setClauses(new Conjunction(new BitStringFixer(C), new Conjunction(
				q)).getClauses());
	}

	public BitStringCover(IBitStringList C, IBitString membership)
			throws Exception
	{
		if (C == null)
			throw new BitStringCoverException(
					"Null passed to constructor as IBitStringList.");
		if (membership == null)
			throw new BitStringCoverException(
					"Null passed to constructor as IBitString.");
		int cLength = C.size();
		if (cLength == 0)
			throw new BitStringCoverException(
					"IBitStringList of zero size passed to constructor.");

		IProblem[] q = new IProblem[C.getBitString(0).size()];
		for (int i = 0; i < C.getBitString(0).size(); i++)
		{
			IProblem[] p = new IProblem[cLength];
			for (int j = 0; j < cLength; j++)
			{
				p[j] = new Conjunction(new BitFixer(
						membership.getBooleanVariable(j), true), new BitFixer(C
						.getBitString(j).getBooleanVariable(i), true));
			}
			q[i] = new Disjunction(p);
		}

		this.setClauses(new Conjunction(new BitStringListFixer(C),
				new Conjunction(q)).getClauses());
	}
}
