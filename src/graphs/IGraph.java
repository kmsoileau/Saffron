package graphs;

import bits.IBooleanVariable;
import graphs.exceptions.GraphException;

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
public interface IGraph extends Cloneable
{
	boolean areConnected(int i, int j);

	void connect(int i, int j);

	void disconnect(int i, int j);

	IBooleanVariable[][] getData();

	IBooleanVariable getData(int i, int j);

	String getName();

	void setData(IBooleanVariable[][] iBooleanVariables);

	void setData(int i, int j, IBooleanVariable booleanVariable);

	void setName(String name) throws GraphException;

	int size();
}
