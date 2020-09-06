/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 10, 2019
 */
package graphs.generalized;

import bits.INaturalNumber;
import naturalnumbers.NNtoNNMapper;

/**
 * 
 *
 */
public interface IDirectedGraph extends IGraph
{

	void disconnect(INaturalNumber n0, INaturalNumber n1) throws Exception;

	@Override
	NNtoNNMapper getBacking();

	void monoconnect(INaturalNumber i, INaturalNumber j) throws Exception;

}
