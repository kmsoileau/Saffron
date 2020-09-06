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

import algebra.semigroups.generalized.SemigroupCommutativer;
import bits.INaturalNumber;

/**
 * 
 *
 */
public class GroupCommutativer extends SemigroupCommutativer
{
	public GroupCommutativer(Group grp) throws Exception
	{
		super(grp);
	}

	public GroupCommutativer(Group grp, INaturalNumber X, INaturalNumber Y)
			throws Exception
	{
		super(grp, X, Y);
	}
}
