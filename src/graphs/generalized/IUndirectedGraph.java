/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 11, 2019
 */
package graphs.generalized;

import bits.INaturalNumber;
import naturalnumbers.NNtoNNMapper;

/**
 * 
 *
 */
public interface IUndirectedGraph
{

	void connect(INaturalNumber i, INaturalNumber j) throws Exception;

	NNtoNNMapper getBacking();

	int size();

}
