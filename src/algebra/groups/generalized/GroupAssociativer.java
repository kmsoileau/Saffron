/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 13, 2019
 */
package algebra.groups.generalized;

import algebra.monoids.generalized.MonoidAssociativer;
import bits.INaturalNumber;

/**
 * 
 *
 */
public class GroupAssociativer extends MonoidAssociativer
{
	public GroupAssociativer(Group grp) throws Exception
	{
		super(grp);
	}

	public GroupAssociativer(Group grp, INaturalNumber X, INaturalNumber Y, INaturalNumber Z) throws Exception
	{
		super(grp, X, Y, Z);
	}
}
