/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 13, 2019
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
public class MagmaAssociativer extends Problem implements IProblem
{
	public MagmaAssociativer(Magma mgm) throws Exception
	{
		int order = mgm.getOrder();

		INaturalNumber[] X = new NaturalNumber[order];
		INaturalNumber[] Y = new NaturalNumber[order];
		INaturalNumber[] Z = new NaturalNumber[order];

		for (int i = 0; i < order; i++)
		{
			X[i] = new NaturalNumber(i);
			Y[i] = new NaturalNumber(i);
			Z[i] = new NaturalNumber(i);
		}

		int index = 0;
		IProblem[] p = new IProblem[order * order * order];
		for (int i = 0; i < order; i++)
			for (int j = 0; j < order; j++)
				for (int k = 0; k < order; k++)
				{
					p[index++] = new MagmaAssociativer(mgm, X[i], Y[j], Z[k]);
				}

		this.setClauses(new Conjunction(p).getClauses());
	}

	public MagmaAssociativer(Magma g, INaturalNumber X, INaturalNumber Y, INaturalNumber Z) throws Exception
	{
		INaturalNumber XY = new NaturalNumber();
		INaturalNumber YZ = new NaturalNumber();
		INaturalNumber XYZ = new NaturalNumber();

		IProblem problem = new Conjunction(new Magmaer(g, X, Y, XY), new Magmaer(g, Y, Z, YZ),
				new Magmaer(g, XY, Z, XYZ), new Magmaer(g, X, YZ, XYZ));

		this.setClauses(problem.getClauses());
	}
}
