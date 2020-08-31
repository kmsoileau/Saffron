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
public class NaturalNumberLeastCommonMultipler extends Problem implements
		IProblem
{
	public NaturalNumberLeastCommonMultipler(INaturalNumber M,
			INaturalNumber N, INaturalNumber LCM) throws Exception
	{
		INaturalNumber K = new NaturalNumber();
		INaturalNumber L = new NaturalNumber();

		IProblem p = new Conjunction(new NaturalNumberMultiplier(M, K, LCM),
				new NaturalNumberMultiplier(N, L, LCM),
				new NaturalNumberRelativelyPrimer(K, L));

		this.setClauses(p.getClauses());
	}
}
