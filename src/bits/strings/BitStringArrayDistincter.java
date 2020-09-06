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
package bits.strings;

import java.util.ArrayList;

import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;

/**
 * 
 *
 */
public class BitStringArrayDistincter extends Problem implements IProblem
{
	public BitStringArrayDistincter(IBitString[] X) throws Exception
	{
		IProblem problem;
		if (X.length == 0)
			problem = Problem.trivialProblem();
		else
		{
			ArrayList<IProblem> p = new ArrayList<IProblem>();

			for (int i = 0; i < X.length; i++)
				for (int j = i + 1; j < X.length; j++)
					p.add(new BitStringUnequalizer(X[i], X[j]));

			problem = new Conjunction(p);
		}

		this.setClauses(problem.getClauses());
	}
}
