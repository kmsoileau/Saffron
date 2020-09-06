/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 8, 2019
 */
package naturalnumbers;

import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

/**
 * Finds relatively prime P and Q such that X/Y = P/Q.
 *
 */
public class NaturalNumberLowestTermser extends Problem implements IProblem
{

	public NaturalNumberLowestTermser(INaturalNumber X, INaturalNumber Y,
			INaturalNumber P, INaturalNumber Q) throws Exception
	{
		INaturalNumber product1 = new NaturalNumber();
		INaturalNumber product2 = new NaturalNumber();

		IProblem problem = new Conjunction(new NaturalNumberRelativelyPrimer(P,
				Q), new NaturalNumberMultiplier(X, Q, product1),
				new NaturalNumberMultiplier(Y, P, product2),
				new NaturalNumberEqualizer(product1, product2));

		this.setClauses(problem.getClauses());
	}

}
