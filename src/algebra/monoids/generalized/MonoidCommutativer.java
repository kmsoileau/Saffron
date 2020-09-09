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

import algebra.semigroups.generalized.SemigroupCommutativer;
import bits.INaturalNumber;

/**
 * 
 *
 */
public class MonoidCommutativer extends SemigroupCommutativer
{
	public MonoidCommutativer(Monoid mnd) throws Exception
	{
		super(mnd);
	}

	public MonoidCommutativer(Monoid mnd, INaturalNumber X, INaturalNumber Y) throws Exception
	{
		super(mnd, X, Y);
	}
}
