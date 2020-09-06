/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Dec 20, 2018
 */
package bits.strings.lists;

import bits.IBitString;

public class TestSetter extends bits.strings.TestSetter
{
	public TestSetter(IBitStringList C, IBitString includedInTestSet)
			throws Exception
	{
		super(C.toArray(), includedInTestSet);
	}
}
