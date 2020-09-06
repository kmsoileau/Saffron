/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 20, 2019
 */
package naturalnumbers;

import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

/**
 * 
 *
 */
public class NaturalNumberDigiter extends Problem implements IProblem
{

	public NaturalNumberDigiter(INaturalNumber n) throws Exception
	{
		INaturalNumber zero = new NaturalNumber(0);
		INaturalNumber nine = new NaturalNumber(9);

		IProblem problem = new Conjunction(new NaturalNumberFixer(zero),
				new NaturalNumberFixer(nine),
				new NaturalNumberOrderer(zero, n), new NaturalNumberOrderer(n,
						nine));

		this.setClauses(problem.getClauses());
	}
}
