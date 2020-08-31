package naturalnumbers;

import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 3, 2005
 */
public class NaturalNumberEvener extends Problem implements IProblem
{
	public NaturalNumberEvener(INaturalNumber Z) throws Exception
	{
		INaturalNumber Two = new NaturalNumber();
		INaturalNumber K = new NaturalNumber();

		IProblem p = new Conjunction(new NaturalNumberFixer(Two, 2),
				new NaturalNumberMultiplier(Two, K, Z));

		this.setClauses(p.getClauses());
	}
}