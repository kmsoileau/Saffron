/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Jan 20, 2019
 */
package bits.strings.lists;

import bits.IBitString;

/**
 * 
 *
 */
public class MinimumTestSetter extends bits.strings.MinimumTestSetter
{
	public MinimumTestSetter(IBitStringList C, int K,
			IBitString includedInTestSet) throws Exception
	{
		super(C.toArray(), K, includedInTestSet);
	}
}
