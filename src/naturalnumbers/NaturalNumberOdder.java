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
 * @since Mar 4, 2005
 */
public class NaturalNumberOdder extends Problem implements IProblem
{
	public NaturalNumberOdder(INaturalNumber Z) throws Exception
	{
		INaturalNumber One = new NaturalNumber();
		INaturalNumber Two = new NaturalNumber();
		INaturalNumber K = new NaturalNumber();
		INaturalNumber L = new NaturalNumber();

		IProblem p = new Conjunction(new NaturalNumberFixer(One, 1),
				new NaturalNumberFixer(Two, 2), new NaturalNumberMultiplier(
						Two, K, L), new NaturalNumberIncrementer(L, Z));

		this.setClauses(p.getClauses());
	}
}