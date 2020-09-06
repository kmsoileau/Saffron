/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 20, 2019
 */
package algebra.magmas.generalized;

import bits.Conjunction;
import bits.Disjunction;
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
public class MagmaHomomorphismer extends Problem implements IProblem
{
	public MagmaHomomorphismer(Magma magma1, Magma magma2, INaturalNumber[] h)
			throws Exception
	{
		int index = 0;
		int order1 = magma1.getOrder();

		INaturalNumber[][] dom = new INaturalNumber[order1][order1];
		INaturalNumber[][] cod = new INaturalNumber[order1][order1];

		INaturalNumber[] X = new INaturalNumber[order1];
		INaturalNumber[] Y = new INaturalNumber[order1];

		IProblem[] xyFixer = new IProblem[order1];

		IProblem[] p = new IProblem[order1 * order1];

		for (int i = 0; i < order1; i++)
		{
			X[i] = new NaturalNumber(i);
			Y[i] = new NaturalNumber(i);
		}

		for (int i = 0; i < order1; i++)
		{
			dom[i] = new NaturalNumber[order1];
			cod[i] = new NaturalNumber[order1];
			for (int j = 0; j < order1; j++)
			{
				dom[i][j] = new NaturalNumber();
				cod[i][j] = new NaturalNumber();
			}
		}

		for (int i = 0; i < order1; i++)
		{
			xyFixer[i] = new Conjunction(new NaturalNumberFixer(X[i]),
					new NaturalNumberFixer(Y[i]));
		}

		for (int i = 0; i < order1; i++)
		{
			for (int j = 0; j < order1; j++)
			{
				INaturalNumber codij = cod[i][j];
				INaturalNumber domij = dom[i][j];
				IProblem[] disj = new IProblem[order1];
				for (int k = 0; k < order1; k++)
					disj[k] = new Conjunction(new NaturalNumberFixer(domij, k),
							new NaturalNumberEqualizer(codij, h[k]));
				p[index++] = new Conjunction(new Magmaer(magma1, X[i], Y[j],
						domij), // domij=i*j
						new Magmaer(magma2, h[i], h[j], codij), // cod[i][j]=h(i)*h(j)
						new Disjunction(disj));
			}
		}

		IProblem problem = new Conjunction(new MagmaFixer(magma1),
				new MagmaFixer(magma2), new Conjunction(xyFixer),
				new Conjunction(p));

		this.setClauses(problem.getClauses());
	}
}
