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
package bitstringgraphs;

import naturalnumbers.NaturalNumberFixer;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import bitstringgraphs.BitStringGraphAsProblem;
import bitstringgraphs.IBitStringGraph;

/**
 * 
 *
 */
public class PathFinder extends Problem implements IProblem
{
	/**
	 * @throws Exception
	 * 
	 */
	public PathFinder(IBitStringGraph G, int startVertex, int endVertex, int K,
			INaturalNumber[] path) throws Exception
	{
		this.setClauses(new Conjunction(new NaturalNumberFixer(path[0],
				startVertex), new NaturalNumberFixer(path[K], endVertex),
				new BitStringGraphAsProblem(G, path)).getClauses());
	}
}
