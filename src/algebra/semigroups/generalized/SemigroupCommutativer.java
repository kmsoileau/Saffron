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
package algebra.semigroups.generalized;

import algebra.magmas.generalized.MagmaCommutativer;
import bits.INaturalNumber;

/**
 * 
 *
 */
public class SemigroupCommutativer extends MagmaCommutativer
{
	public SemigroupCommutativer(Semigroup smgrp) throws Exception
	{
		super(smgrp);
	}

	public SemigroupCommutativer(Semigroup smgrp, INaturalNumber X, INaturalNumber Y)
			throws Exception
	{
		super(smgrp, X, Y);
	}
}
