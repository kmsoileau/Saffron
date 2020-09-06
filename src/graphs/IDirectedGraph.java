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

import java.util.ArrayList;

/**
 * 
 *
 */
public interface IDirectedGraph extends IGraph
{
	void biconnect(int i, int j);

	void monoconnect(int i, int j);

	ArrayList<Integer> neighbors(int i);

	@Override
	String toString();

}
