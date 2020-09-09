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
public class NaturalNumberGreatestCommonDivisor extends Problem implements IProblem
{
	public NaturalNumberGreatestCommonDivisor(INaturalNumber M, INaturalNumber N, INaturalNumber GCD) throws Exception
	{
		INaturalNumber K = new NaturalNumber();
		INaturalNumber L = new NaturalNumber();

		IProblem p = new Conjunction(new NaturalNumberMultiplier(GCD, K, M), new NaturalNumberMultiplier(GCD, L, N),
				new NaturalNumberRelativelyPrimer(K, L));

		this.setClauses(p.getClauses());
	}
}
