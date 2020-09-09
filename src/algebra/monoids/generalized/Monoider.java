package algebra.monoids.generalized;

import algebra.semigroups.generalized.Semigrouper;
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
public class Monoider extends Semigrouper
{
	public Monoider(int[][] opTable, INaturalNumber X, INaturalNumber Y, INaturalNumber Z) throws Exception
	{
		super(opTable, X, Y, Z);
	}

	public Monoider(Monoid mnd, INaturalNumber X, INaturalNumber Y, INaturalNumber Z) throws Exception
	{
		super(mnd, X, Y, Z);
	}
}
