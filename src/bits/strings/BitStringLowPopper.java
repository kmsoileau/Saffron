package bits.strings;

import bits.BitEqualizer;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import bits.strings.exceptions.BitStringLowPopperException;

/**
 * An extension of the <code>Problem</code> class which constrains parameter
 * <code>Y</code> to equal the <code>IBitString</code> obtained when the bit of
 * lowest index in <code>X</code> is dropped. Naturally, the size of
 * <code>Y</code> must be one less than the size of <code>X</code>.
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2005/04/21
 */
public class BitStringLowPopper extends Problem implements IProblem
{
	public BitStringLowPopper(IBitString X, IBitString Y) throws Exception
	{
		if (X.size() != Y.size() + 1)
			throw new BitStringLowPopperException("X.size() != Y.size() + 1");
		// this.setClauses(Problem.unsolvableProblem().getClauses());
		else
		{
			IProblem[] p = new IProblem[X.size()];
			int count = 0;
			for (int i = 0; i < p.length - 1; i++)
				p[count++] = new BitEqualizer(X.getBooleanVariable(i + 1), Y.getBooleanVariable(i));
			this.setClauses(new Conjunction(p).getClauses());
		}
	}
}