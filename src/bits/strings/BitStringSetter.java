/*
 * Constrains all <code>IBooleanVariable</code>s of <code>IBitString b</code> to
 * equal the boolean value <code>true</code>.
 * 
 * Apr 15, 2005
 *
 */

package bits.strings;

import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import bits.strings.exceptions.BitStringSetterException;

public class BitStringSetter extends Problem implements IProblem
{
	public BitStringSetter(IBitString X) throws Exception
	{
		if (X == null)
			throw new BitStringSetterException("A null IBitString was passed to a constructor.");
		else
		{
			int commonsize = X.size();
			IProblem[] p = new IProblem[commonsize];
			for (int i = 0; i < commonsize; i++)
				p[i] = new BitStringBitFixer(X, i, true);
			this.setClauses(new Conjunction(p).getClauses());
		}
	}
}