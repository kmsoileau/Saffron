package bits.strings;

import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;

/**
 * <code>C</code> is a collection of <code>IBitString</code>s, each of size
 * <code>n</code>. <code>B</code> is a collection of <code>IBitString</code>s,
 * each of size <code>n</code>. This <code>IProblem</code> is satisfied if and
 * only if for each <code>C[i]</code>, the <code>IBitString included[i]</code>
 * describes a subset of <code>B</code> such that the bitwise <code>OR</code> of
 * the members of this subset evaluates to <code>C[i]</code>. In effect,
 * <code>B</code> is a basis for <code>C</code>.
 * 
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Dec 28, 2018
 * 
 */
public class BitStringArrayBasiser extends Problem implements IProblem
{
	public BitStringArrayBasiser(IBitString[] C, IBitString[] B, IBitString[] included) throws Exception
	{
		int size = C.length;
		IProblem[] p = new IProblem[size];
		for (int i = 0; i < size; i++)
		{
			p[i] = new BitStringConditionalOrer(B, included[i], C[i]);
		}

		this.setClauses(new Conjunction(p).getClauses());
	}
}
