/**
 *
 * @author Kerry Michael Soileau
 * <p>
 * email: ksoileau2@yahoo.com
 * <p>
 * website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 14, 2019
 */
package graphs.generalized;

import java.util.ArrayList;

import bits.BitFixer;
import bits.Conjunction;
import bits.Disjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import graphs.generalized.exceptions.GraphEdgerException;
import naturalnumbers.NaturalNumberEqualizer;
import naturalnumbers.NaturalNumberPair;

/**
 *
 *
 */
public class GraphEdger extends Problem implements IProblem
{
	/**
	 * Satisfied if <code>predecessor</code> --&gt; <code>successor</code> in
	 * <code>graph</code>.
	 *
	 * @param graph       IGeneralizedGraph
	 * @param predecessor INaturalNumber
	 * @param successor   INaturalNumber
	 * @throws Exception _
	 */
	public GraphEdger(IGraph graph, INaturalNumber predecessor, INaturalNumber successor) throws Exception
	{
		if (graph == null)
			throw new GraphEdgerException("Null IGeneralizedGraph passed to constructor.");
		if (predecessor == null && successor == null)
			throw new GraphEdgerException("Null INaturalNumber passed to constructor.");
		ArrayList<IProblem> p = new ArrayList<IProblem>();
		ArrayList<NaturalNumberPair> pairs = graph.getPairs();
		for (NaturalNumberPair cpr : pairs)
		{
			INaturalNumber currP = cpr.getFirst();
			INaturalNumber currS = cpr.getSecond();
			// if currP --&gt; currS then (predecessor == currP and successor ==
			// currS)
			p.add(new Disjunction(new BitFixer(graph.getData(currP, currS), false), new Conjunction(
					new NaturalNumberEqualizer(predecessor, currP), new NaturalNumberEqualizer(successor, currS))));
		}

		this.setClauses(new Disjunction(p).getClauses());
	}
}
