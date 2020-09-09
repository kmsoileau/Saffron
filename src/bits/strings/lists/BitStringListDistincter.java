/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Jan 21, 2019
 */
package bits.strings.lists;

import bits.Conjunction;
import bits.IProblem;
import bits.Problem;
import bits.strings.BitStringUnequalizer;

/**
 * 
 *
 */
public class BitStringListDistincter extends Problem implements IProblem
{
	public BitStringListDistincter(IBitStringList X) throws Exception
	{
		int size = X.size();

		IProblem problem = null;

		for (int i = 0; i < size; i++)
			for (int j = i + 1; j < size; j++)
				problem = new Conjunction(problem, new BitStringUnequalizer(X.getBitString(i), X.getBitString(j)));

		this.setClauses(problem.getClauses());
	}
}
