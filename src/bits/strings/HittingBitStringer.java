package bits.strings;

import bits.Conjunction;
import bits.IBitString;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.BitStringTotaler;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;

/**
 * Does there exist an <code>IBitString hittingSet</code> such that for every
 * <code>C[i]</code>, there exists some integer <code>pos[i]</code> such that
 * <code>hittingSet</code> and <code>C[i]</code> have
 * <code>IBooleanVariable</code>s at position <code>pos[i]</code> that evaluate
 * to <code>true</code>?
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2018/12/13
 */
public class HittingBitStringer extends Problem implements IProblem
{
	public HittingBitStringer(IBitString[] C, IBitString hittingSet) throws Exception
	{
		int problemIndex = 0;
		IProblem[] problemArray = new IProblem[C.length];
		for (int i = 0; i < C.length; i++)
			problemArray[problemIndex++] = new BitStringIntersector(C[i], hittingSet);
		this.setClauses(new Conjunction(problemArray).getClauses());
	}

	public HittingBitStringer(IBitString[] C, INaturalNumber bitSum, IBitString Y) throws Exception
	{
		this.setClauses(new Conjunction(new HittingBitStringer(C, Y), new BitStringTotaler(Y, bitSum)).getClauses());
	}

	public HittingBitStringer(IBitString[] C, int K, IBitString hittingSet) throws Exception
	{
		INaturalNumber bitSum = new NaturalNumber(K);

		this.setClauses(new Conjunction(new NaturalNumberFixer(bitSum), new HittingBitStringer(C, hittingSet),
				new BitStringTotaler(hittingSet, bitSum)).getClauses());
	}
}