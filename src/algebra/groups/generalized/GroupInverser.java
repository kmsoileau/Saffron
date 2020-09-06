/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 12, 2019
 */
package algebra.groups.generalized;

import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

/**
 * 
 *
 */
public class GroupInverser extends Problem implements IProblem
{
	public GroupInverser(Group grp, INaturalNumber E, INaturalNumber EInverse,
			INaturalNumber Identity) throws Exception
	{
		this.setClauses(new Grouper(grp, E, EInverse, Identity).getClauses());
	}
}
