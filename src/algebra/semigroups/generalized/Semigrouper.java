package algebra.semigroups.generalized;

import algebra.magmas.generalized.Magmaer;
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
public class Semigrouper extends Magmaer
{
	public Semigrouper(int[][] opTable, INaturalNumber X, INaturalNumber Y,
			INaturalNumber Z) throws Exception
	{
		super(opTable, X, Y, Z);

	}

	public Semigrouper(Semigroup smgrp, INaturalNumber X, INaturalNumber Y,
			INaturalNumber Z) throws Exception
	{
		super(smgrp, X, Y, Z);
	}
}
