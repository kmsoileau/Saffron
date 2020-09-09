package bits.strings;

import bits.BitSubtractor;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import bits.strings.exceptions.BitStringSubtractorException;

/**
 * 
 * Constrains the IBitString Z to be the bitwise subtraction of IBitString Y
 * from IBitString X.
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.1
 * @since Mar 5, 2019
 */
public class BitStringSubtractor extends Problem implements IProblem
{
	public BitStringSubtractor(IBitString X, IBitString Y, IBitString Z) throws Exception
	{
		if ((X.size() != Y.size()) || (X.size() != Z.size()))
			throw new BitStringSubtractorException("X, Y and Z are not of equal size.");
		else
		{
			int commonsize = X.size();
			IProblem[] p = new IProblem[commonsize];
			int count = 0;
			for (int i = 0; i < commonsize; i++)
				p[count++] = new BitSubtractor(X.getBooleanVariable(i), Y.getBooleanVariable(i),
						Z.getBooleanVariable(i));
			this.setClauses(new Conjunction(p).getClauses());
		}
	}
}