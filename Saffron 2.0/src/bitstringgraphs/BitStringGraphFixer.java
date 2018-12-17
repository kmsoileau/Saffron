package bitstringgraphs;

import bits.BitFixer;
import bits.Conjunction;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;

/**
 * An extension of the IProblem class which fixes the values of an
 * BitStringGraph.
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2006/03/31
 */
public class BitStringGraphFixer extends Problem
{
	public BitStringGraphFixer(IBitStringGraph graph) throws Exception
	{
		IBooleanVariable[][] work = graph.getData();
		IProblem problem = Problem.newProblem();
		int len = work.length;
		for (int i = 0; i < len; i++)
		{
			IBooleanVariable[] wi = work[i];
			for (int j = 0; j < len; j++)
				problem = new Conjunction(problem, new BitFixer(wi[j]));
		}
		this.setClauses(problem.getClauses());
	}
}
