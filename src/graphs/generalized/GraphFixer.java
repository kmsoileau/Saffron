/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 15, 2019
 */
package graphs.generalized;

import java.util.ArrayList;
import java.util.HashMap;

import bits.BitFixer;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import graphs.generalized.exceptions.GraphFixerException;
import naturalnumbers.NaturalNumberFixer;

/**
 * 
 *
 */
public class GraphFixer extends Problem implements IProblem
{
	public GraphFixer(IGraph graph) throws Exception
	{
		if (graph == null)
			throw new GraphFixerException(
					"Null IGeneralizedGraph passed to constructor.");

		ArrayList<IProblem> p = new ArrayList<IProblem>();
		HashMap<INaturalNumber, HashMap<INaturalNumber, IBooleanVariable>> work = graph
				.getBacking().getLookup();
		IProblem problem = new NaturalNumberFixer(graph.getVertices().values());

		for (INaturalNumber nn : work.keySet())
		{
			HashMap<INaturalNumber, IBooleanVariable> wi = work.get(nn);
			for (INaturalNumber j : wi.keySet())
				p.add(new BitFixer(wi.get(j)));
		}

		this.setClauses(problem.getClauses());
	}
}
