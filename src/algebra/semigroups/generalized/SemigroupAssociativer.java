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
package algebra.semigroups.generalized;

import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.NaturalNumber;

/**
 * 
 *
 */
public class SemigroupAssociativer extends Problem implements IProblem
{
	public SemigroupAssociativer(Semigroup smgrp) throws Exception
	{
		int order = smgrp.getOrder();

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
					p[index++] = new SemigroupAssociativer(smgrp, X[i], Y[j], Z[k]);
				}

		this.setClauses(new Conjunction(p).getClauses());
	}

	public SemigroupAssociativer(Semigroup smgrp, INaturalNumber X,
			INaturalNumber Y, INaturalNumber Z) throws Exception
	{
		INaturalNumber XY = new NaturalNumber();
		INaturalNumber YZ = new NaturalNumber();
		INaturalNumber XYZ = new NaturalNumber();

		IProblem problem = new Conjunction(new Semigrouper(smgrp, X, Y, XY),
				new Semigrouper(smgrp, Y, Z, YZ), new Semigrouper(smgrp, XY, Z, XYZ),
				new Semigrouper(smgrp, X, YZ, XYZ));

		this.setClauses(problem.getClauses());
	}
}
