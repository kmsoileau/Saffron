/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Dec 20, 2018
 */
package naturalnumbers.minimumcover;

import bits.Conjunction;
import bits.IBitString;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import bits.strings.BitStringSizedCoverer;
import bits.strings.lists.IBitStringList;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberOrderer;
import naturalnumbers.exceptions.MinimumCoverException;

/*
 * Given a collection C of BitStrings each of size n and a positive integer K, 
 * does there exist a subset of C of size no more than K such that the bitwise 
 * OR of the members of the subset evaluate to the BitString 111...1 of size n?
 */
public class MinimumSizedCoverer extends Problem implements IProblem
{

	public MinimumSizedCoverer(IBitString[] C, IBitString included, INaturalNumber K) throws Exception
	{
		if (C == null)
			throw new MinimumCoverException("Null passed to constructor as IBitStringList.");
		if (included == null)
			throw new MinimumCoverException("Null passed to constructor as IBitString.");
		if (K == null)
			throw new MinimumCoverException("Null passed to constructor as INaturalNumber.");
		if (C.length == 0)
			throw new MinimumCoverException("IBitStringList of zero length passed to constructor.");

		INaturalNumber sizeOfCover = new NaturalNumber();
		IProblem problem = new Conjunction(new NaturalNumberOrderer(sizeOfCover, K),
				new BitStringSizedCoverer(C, included, sizeOfCover));
		this.setClauses(problem.getClauses());
	}

	public MinimumSizedCoverer(IBitStringList C, IBitString included, INaturalNumber K) throws Exception
	{
		if (C == null)
			throw new MinimumCoverException("Null passed to constructor as IBitStringList.");
		if (included == null)
			throw new MinimumCoverException("Null passed to constructor as IBitString.");
		if (K == null)
			throw new MinimumCoverException("Null passed to constructor as INaturalNumber.");
		if (C.size() == 0)
			throw new MinimumCoverException("IBitStringList of zero length passed to constructor.");

		INaturalNumber sizeOfCover = new NaturalNumber();
		IProblem problem = new Conjunction(new NaturalNumberOrderer(sizeOfCover, K),
				new bits.strings.lists.BitStringSizedCoverer(C, included, sizeOfCover));
		this.setClauses(problem.getClauses());
	}
}