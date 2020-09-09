package algebra.groups.generalized;

import algebra.monoids.generalized.Monoider;
import bits.INaturalNumber;

/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 8, 2019
 */

/**
 * 
 *
 */
public class Grouper extends Monoider
{
	public Grouper(Group grp, INaturalNumber X, INaturalNumber Y, INaturalNumber Z) throws Exception
	{
		super(grp, X, Y, Z);
	}

	public Grouper(int[][] opTable, INaturalNumber X, INaturalNumber Y, INaturalNumber Z) throws Exception
	{
		super(opTable, X, Y, Z);
	}
}
