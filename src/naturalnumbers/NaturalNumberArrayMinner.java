/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 25, 2019
 */
package naturalnumbers;

import bits.Conjunction;
import bits.Disjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

/**
 * 
 *
 */
public class NaturalNumberArrayMinner extends Problem implements IProblem
{
	public NaturalNumberArrayMinner(INaturalNumber[] d, INaturalNumber minEntry)
			throws Exception
	{
		int n = d.length;

		IProblem[] q = new IProblem[n];
		for (int i = 0; i < n; i++)
		{
			IProblem[] p = new IProblem[n - 1];
			INaturalNumber cand = d[i];
			int index = 0;
			for (int j = 0; j < n; j++)
			{
				if (j == i)
					continue;
				p[index++] = new NaturalNumberOrderer(cand, d[j]);
			}
			q[i] = new Conjunction(new Conjunction(p),
					new NaturalNumberEqualizer(minEntry, cand));
		}

		this.setClauses(new Disjunction(q).getClauses());
	}

	public NaturalNumberArrayMinner(INaturalNumber[] d, INaturalNumber minIndex,
			INaturalNumber minxEntry) throws Exception
	{
		int n = d.length;

		IProblem[] q = new IProblem[n];
		for (int i = 0; i < n; i++)
		{
			IProblem[] p = new IProblem[n - 1];
			INaturalNumber cand = d[i];
			int index = 0;
			for (int j = 0; j < n; j++)
			{
				if (j == i)
					continue;
				p[index++] = new NaturalNumberOrderer(cand, d[j]);
			}
			q[i] = new Conjunction(new Conjunction(p), new NaturalNumberFixer(
					minIndex, i), new NaturalNumberEqualizer(minxEntry, cand));
		}

		this.setClauses(new Disjunction(q).getClauses());
	}
}
