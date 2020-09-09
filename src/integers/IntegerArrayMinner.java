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
package integers;

import bits.Conjunction;
import bits.Disjunction;
import bits.IProblem;
import bits.Problem;

/**
 * 
 *
 */
public class IntegerArrayMinner extends Problem implements IProblem
{
	public IntegerArrayMinner(IInteger[] d, IInteger minEntry) throws Exception
	{
		int n = d.length;

		IProblem[] q = new IProblem[n];
		for (int i = 0; i < n; i++)
		{
			IProblem[] p = new IProblem[n - 1];
			IInteger cand = d[i];
			int index = 0;
			for (int j = 0; j < n; j++)
			{
				if (j == i)
					continue;
				p[index++] = new IntegerOrderer(cand, d[j]);
			}
			q[i] = new Conjunction(new Conjunction(p), new IntegerEqualizer(minEntry, cand));
		}

		this.setClauses(new Disjunction(q).getClauses());
	}

	public IntegerArrayMinner(IInteger[] d, IInteger minIndex, IInteger minxEntry) throws Exception
	{
		int n = d.length;

		IProblem[] q = new IProblem[n];
		for (int i = 0; i < n; i++)
		{
			IProblem[] p = new IProblem[n - 1];
			IInteger cand = d[i];
			int index = 0;
			for (int j = 0; j < n; j++)
			{
				if (j == i)
					continue;
				p[index++] = new IntegerOrderer(cand, d[j]);
			}
			q[i] = new Conjunction(new Conjunction(p), new IntegerFixer(minIndex, i),
					new IntegerEqualizer(minxEntry, cand));
		}

		this.setClauses(new Disjunction(q).getClauses());
	}
}
