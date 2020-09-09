/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 11, 2019
 */
package algebra.monoids.generalized;

import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberEqualizer;
import naturalnumbers.NaturalNumberFixer;

/**
 * 
 *
 */
public class MonoidIdentityer extends Problem implements IProblem
{
	public MonoidIdentityer(int[][] opTable, INaturalNumber Identity) throws Exception
	{
		int order = (int) Math.sqrt(opTable.length);

		INaturalNumber[] E1 = new INaturalNumber[order];
		INaturalNumber[] E2 = new INaturalNumber[order];
		INaturalNumber[] E3 = new INaturalNumber[order];

		Monoider[] mArray = new Monoider[order];

		for (int i = 0; i < order; i++)
		{
			E1[i] = new NaturalNumber();
			E2[i] = new NaturalNumber();
			E3[i] = new NaturalNumber();
			mArray[i] = new Monoider(opTable, E1[i], E2[i], E3[i]);
		}

		IProblem[] p = new IProblem[order];
		for (int i = 0; i < order; i++)
			p[i] = new Conjunction(mArray[i], new NaturalNumberFixer(E1[i], i),
					new NaturalNumberEqualizer(E2[i], Identity), new NaturalNumberFixer(E3[i], i));

		IProblem problem = new Conjunction(new Conjunction(mArray), new Conjunction(p));

		this.setClauses(problem.getClauses());
	}

	public MonoidIdentityer(Monoid mnd, INaturalNumber Identity) throws Exception
	{
		int order = mnd.getOrder();

		INaturalNumber[] E1 = new INaturalNumber[order];
		INaturalNumber[] E2 = new INaturalNumber[order];
		INaturalNumber[] E3 = new INaturalNumber[order];

		IProblem[] mArray = new Monoider[order];

		for (int i = 0; i < order; i++)
		{
			E1[i] = new NaturalNumber();
			E2[i] = new NaturalNumber();
			E3[i] = new NaturalNumber();
			mArray[i] = new Monoider(mnd, E1[i], E2[i], E3[i]);
		}

		IProblem[] p = new IProblem[order];
		for (int i = 0; i < order; i++)
			p[i] = new Conjunction(mArray[i], new NaturalNumberFixer(E1[i], i),
					new NaturalNumberEqualizer(E2[i], Identity), new NaturalNumberFixer(E3[i], i));

		IProblem problem = new Conjunction(new Conjunction(mArray), new Conjunction(p));

		this.setClauses(problem.getClauses());
	}
}
