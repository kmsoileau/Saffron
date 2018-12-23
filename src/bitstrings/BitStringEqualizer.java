package bitstrings;

import bits.BitEqualizer;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import exceptions.bitstrings.BitStringEqualizerException;

/**
 * Constrains <code>IBitString X</code> to equal <code>IBitString</code>
 * <code>Y</code>.
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2005/04/15
 */
public class BitStringEqualizer extends Problem implements IProblem
{
	public BitStringEqualizer(IBitString X, IBitString Y) throws Exception
	{
		/*
		 * if(X.size()!=Y.size())
		 * this.setClauses(Problem.unsolvableProblem().getClauses());
		 */
		if (X.size() != Y.size())
			throw new BitStringEqualizerException(
					"IBitStrings of unequal size were passed to the constructor.");
		else
		{
			int commonsize = X.size();
			IProblem[] p = new IProblem[commonsize];
			int count = 0;
			for (int i = 0; i < commonsize; i++)
				p[count++] = new BitEqualizer(X.getBooleanVariable(i),
						Y.getBooleanVariable(i));
			this.setClauses(new Conjunction(p).getClauses());
		}
	}
}