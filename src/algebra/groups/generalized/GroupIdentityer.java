/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 11, 2019
 */
package algebra.groups.generalized;

import algebra.monoids.generalized.MonoidIdentityer;
import bits.INaturalNumber;

/**
 * 
 *
 */
public class GroupIdentityer extends MonoidIdentityer
{
	public GroupIdentityer(Group grp, INaturalNumber Identity) throws Exception
	{
		this(grp.toOpTable(), Identity);
	}

	public GroupIdentityer(int[][] opTable, INaturalNumber Identity)
			throws Exception
	{
		super(opTable, Identity);
	}
}
