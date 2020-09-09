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
public class NaturalNumberArrayMaxer extends Problem implements IProblem
{
	public NaturalNumberArrayMaxer(INaturalNumber[] d, INaturalNumber maxEntry) throws Exception
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
				p[index++] = new NaturalNumberOrderer(d[j], cand);
			}
			q[i] = new Conjunction(new Conjunction(p), new NaturalNumberEqualizer(maxEntry, cand));
		}

		this.setClauses(new Disjunction(q).getClauses());
	}

	public NaturalNumberArrayMaxer(INaturalNumber[] d, INaturalNumber maxIndex, INaturalNumber maxEntry)
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
				p[index++] = new NaturalNumberOrderer(d[j], cand);
			}
			q[i] = new Conjunction(new Conjunction(p), new NaturalNumberFixer(maxIndex, i),
					new NaturalNumberEqualizer(maxEntry, cand));
		}

		this.setClauses(new Disjunction(q).getClauses());
	}
}
