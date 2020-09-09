package graphs;

import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.NaturalNumberFixer;

/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Jan 17, 2019
 */
public class PathFinder extends Problem implements IProblem
{
	/**
	 * @param g           - IGraph
	 * @param startVertex - int
	 * @param endVertex   - int
	 * @param K           - int
	 * @param path        - INaturalNumber[]
	 * 
	 * @throws Exception -
	 */
	public PathFinder(IDirectedGraph g, int startVertex, int endVertex, int K, INaturalNumber[] path) throws Exception
	{
		this.setClauses(new Conjunction(new NaturalNumberFixer(path[0], startVertex),
				new NaturalNumberFixer(path[K], endVertex), new GraphAsProblem(g, path)).getClauses());
	}
}
