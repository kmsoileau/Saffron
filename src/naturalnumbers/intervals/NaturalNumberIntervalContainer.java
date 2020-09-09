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
package naturalnumbers.intervals;

import bits.Conjunction;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.NaturalNumberOrderer;

/**
 * 
 *
 */
public class NaturalNumberIntervalContainer extends Problem implements IProblem
{
	public NaturalNumberIntervalContainer(INaturalNumberInterval X, INaturalNumberInterval Y) throws Exception
	{
		IProblem p = new Conjunction(new NaturalNumberOrderer(Y.getLower(), X.getLower()),
				new NaturalNumberOrderer(X.getUpper(), Y.getUpper()));

		this.setClauses(p.getClauses());
	}
}
