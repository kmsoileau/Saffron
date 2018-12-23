package naturalnumbers;

import bits.BitFixer;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBitString;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import bitstrings.BitStringDisjointer;

/**
 * A collection C of sets and a positive integer K: Does C include K mutually
 * disjoint sets?
 * 
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2018/12/12
 */
public class BitStringPacker extends Problem implements IProblem
{
	public BitStringPacker(IBitString[] C, INaturalNumber K,
			IBitString membership) throws Exception
	{
		int problemIndex = 0;
		IProblem[] problemArray = new IProblem[(C.length - 1) * C.length / 2
				+ 1];

		problemArray[problemIndex++] = new BitStringTotaler(membership, K);

		for (int i = 0; i < C.length - 1; i++)
			for (int j = i + 1; j < C.length; j++)
				problemArray[problemIndex++] = new Disjunction(new BitFixer(
						membership.getBooleanVariable(i), false), new BitFixer(
						membership.getBooleanVariable(j), false),
						new BitStringDisjointer(C[i], C[j]));

		this.setClauses(new Conjunction(problemArray).getClauses());
	}
}
