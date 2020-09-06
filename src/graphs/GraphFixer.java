package graphs;

import java.util.ArrayList;

import bits.BitFixer;
import bits.Conjunction;
import bits.IBooleanVariable;
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
 * @since Mar 31, 2006
 */
public class GraphFixer extends Problem
{
	public GraphFixer(IGraph g) throws Exception
	{
		IBooleanVariable[][] work = g.getData();

		ArrayList<IProblem> p = new ArrayList<IProblem>();
		int len = work.length;
		for (int i = 0; i < len; i++)
		{
			IBooleanVariable[] wi = work[i];

			for (int j = 0; j < len; j++)
				p.add(new BitFixer(wi[j]));
		}
		this.setClauses(new Conjunction(p).getClauses());
	}
}