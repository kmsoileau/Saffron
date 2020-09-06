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
package graphs;

import java.util.Set;

import bits.IBooleanVariable;
import bits.INaturalNumber;

/**
 * 
 *
 */
public interface IGeneralizedBitString
{

	IBooleanVariable getBooleanVariable(INaturalNumber deletedVertex);

	Set<INaturalNumber> keySet();

}
