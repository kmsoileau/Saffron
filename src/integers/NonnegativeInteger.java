/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 27, 2019
 */
package integers;

import bits.BitFixer;
import bits.IProblem;
import bits.Problem;

/**
 * 
 *
 */
public class NonnegativeInteger extends Problem implements IProblem
{
	public NonnegativeInteger(IInteger N) throws Exception
	{
		this.setClauses(new BitFixer(N.getSign(), true).getClauses());
	}
}
