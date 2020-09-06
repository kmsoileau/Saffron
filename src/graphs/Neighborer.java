/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 6, 2019
 */
package graphs;

import bits.BitFixer;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.BitStringConditionalOrer;

/**
 * 
 *
 */
public class Neighborer extends Problem implements IProblem
{
	/**
	 * 
	 * Sets the IBitString neighbors to indicate the neighbors of any of the
	 * vertices indicated by homes.
	 * 
	 * @param graph
	 *            IUndirectedGraph
	 * @param homes
	 *            IBitString
	 * @param neighbors
	 *            IBitString
	 * @throws Exception
	 *             _
	 * 
	 */
	public Neighborer(IUndirectedGraph graph, IBitString homes,
			IBitString neighbors) throws Exception
	{
		IProblem[] p = new IProblem[graph.size()];
		IBitString[] nb = new IBitString[graph.size()];
		for (int i = 0; i < graph.size(); i++)
		{
			nb[i] = new BitString(graph.size());
		}

		for (int i = 0; i < graph.size(); i++)
		{
			p[i] = new Neighborer(graph, i, nb[i]);
		}

		IProblem problem = new Conjunction(new Conjunction(p),
				new BitStringConditionalOrer(nb, homes, neighbors));

		this.setClauses(problem.getClauses());
	}

	/**
	 * 
	 * Sets the neighbors IBitString to indicate the neighbors of vertex i in
	 * graph.
	 * 
	 * @param graph
	 *            IUndirectedGraph
	 * @param i
	 *            int
	 * @param neighbors
	 *            IBitString
	 * @throws Exception
	 *             _
	 */
	public Neighborer(IUndirectedGraph graph, int i, IBitString neighbors)
			throws Exception
	{
		IProblem[] p = new IProblem[graph.size()];
		for (int j = 0; j < graph.size(); j++)
		{
			// if i->j then j is a member of neighbors
			// if !i->j the j is not a member of neighbors
			// if graph.getData(i, j) then neighbors.getBooleanVariable(j)==true
			// if !graph.getData(i, j) then
			// neighbors.getBooleanVariable(j)==false
			// graph.getData(i, j)==false or
			// neighbors.getBooleanVariable(j)==true
			// graph.getData(i, j)==true or
			// neighbors.getBooleanVariable(j)==false
			p[j] = new Conjunction(new Disjunction(new BitFixer(graph.getData(i, j),
					false), new BitFixer(neighbors.getBooleanVariable(j), true)), new Disjunction(new BitFixer(graph.getData(i, j),
							true), new BitFixer(neighbors.getBooleanVariable(j), false)));
		}

		this.setClauses(new Conjunction(p).getClauses());
	}
}
