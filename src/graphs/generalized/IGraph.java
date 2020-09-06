/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 4, 2019
 */
package graphs.generalized;

import java.util.ArrayList;
import java.util.HashMap;

import bits.IBooleanVariable;
import bits.INaturalNumber;
import naturalnumbers.NNtoNNMapper;
import naturalnumbers.NaturalNumberPair;

/**
 * 
 *
 */
public interface IGraph
{
	NNtoNNMapper getBacking();

	HashMap<INaturalNumber, HashMap<INaturalNumber, IBooleanVariable>> getData();

	IBooleanVariable getData(INaturalNumber i, INaturalNumber j)
			throws Exception;

	HashMap<INaturalNumber, HashMap<INaturalNumber, IBooleanVariable>> getLookup();

	ArrayList<NaturalNumberPair> getPairs();

	HashMap<Integer, INaturalNumber> getVertices();

	int size();
}
