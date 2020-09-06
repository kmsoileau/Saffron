package graphs;

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
 * @since Mar 1, 2019
 */
public class GraphSquareRooter extends Problem implements IProblem
{
	public GraphSquareRooter(IGraph g, IGraph sqrtG) throws Exception
	{
		this.setClauses(new GraphSquarer(sqrtG, g).getClauses());
	}
}
