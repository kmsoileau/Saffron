/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 14, 2019
 */
package algebra.magmas.generalized;

import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.NaturalNumber;

/**
 * 
 *
 */
public class MagmaCommutativer extends Problem implements IProblem
{
	public MagmaCommutativer(Magma mgm) throws Exception
	{
		int order = mgm.getOrder();

		INaturalNumber[] X = new NaturalNumber[order];
		INaturalNumber[] Y = new NaturalNumber[order];

		for (int i = 0; i < order; i++)
		{
			X[i] = new NaturalNumber(i);
			Y[i] = new NaturalNumber(i);
		}

		int index = 0;
		IProblem[] p = new IProblem[order * order];
		for (int i = 0; i < order; i++)
			for (int j = 0; j < order; j++)
			{
				p[index++] = new MagmaCommutativer(mgm, X[i], Y[j]);
			}

		this.setClauses(new Conjunction(p).getClauses());
	}

	public MagmaCommutativer(Magma g, INaturalNumber X, INaturalNumber Y)
			throws Exception
	{
		INaturalNumber Product = new NaturalNumber();

		IProblem problem = new Conjunction(new Magmaer(g, X, Y, Product),
				new Magmaer(g, Y, X, Product));

		this.setClauses(problem.getClauses());
	}
}
