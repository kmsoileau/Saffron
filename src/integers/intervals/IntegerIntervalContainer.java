/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 5, 2019
 */
package integers.intervals;

import bits.Conjunction;
import bits.IProblem;
import bits.Problem;
import integers.IntegerOrderer;

/**
 * 
 *
 */
public class IntegerIntervalContainer extends Problem implements IProblem
{
	public IntegerIntervalContainer(IIntegerInterval X, IIntegerInterval Y)
			throws Exception
	{
		IProblem p = new Conjunction(new IntegerOrderer(Y.getLower(),
				X.getLower()), new IntegerOrderer(X.getUpper(), Y.getUpper()));

		this.setClauses(p.getClauses());
	}
}
