package bits.strings.lists;

import bits.Conjunction;
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
	public BitStringArrayBasiser(IBitStringList C, IBitStringList B,
			IBitStringList included) throws Exception
	{
		int size = C.size();
		IProblem[] p = new IProblem[size];
		for (int i = 0; i < size; i++)
		{
			p[i] = new BitStringConditionalOrer(B, included.getBitString(i),
					C.getBitString(i));
		}

		this.setClauses(new Conjunction(p).getClauses());
	}
}
