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

/**
 * 
 *
 */
public interface IUndirectedGraph extends IGraph
{
	@Override
	void connect(int i, int j);

	@Override
	void disconnect(int i, int j);

	@Override
	String toString();

}
