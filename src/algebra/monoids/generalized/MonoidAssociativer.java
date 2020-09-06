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
package algebra.monoids.generalized;

import algebra.semigroups.generalized.SemigroupAssociativer;
import bits.INaturalNumber;

/**
 * 
 *
 */
public class MonoidAssociativer extends SemigroupAssociativer
{
	public MonoidAssociativer(Monoid mnd) throws Exception
	{
		super(mnd);
	}

	public MonoidAssociativer(Monoid mnd, INaturalNumber X, INaturalNumber Y,
			INaturalNumber Z) throws Exception
	{
		super(mnd, X, Y, Z);
	}
}
