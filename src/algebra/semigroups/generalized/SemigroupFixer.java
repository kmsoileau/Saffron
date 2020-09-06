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
package algebra.semigroups.generalized;

import java.util.HashMap;

import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.NaturalNumberFixer;

/**
 * 
 *
 */
public class SemigroupFixer extends Problem implements IProblem
{
	public SemigroupFixer(Semigroup smgrp) throws Exception
	{
		// Fixes all of the INaturalNumbers in the
		// mnd.getComposition().keySet(),
		// these are assumed to be all of the elements in the semigroup.
		HashMap<INaturalNumber, HashMap<INaturalNumber, INaturalNumber>> hm1 = smgrp
				.getComposition();
		int index = 0;
		IProblem[] p = new IProblem[smgrp.getOrder()];
		for (INaturalNumber e1 : hm1.keySet())
		{
			p[index++] = new NaturalNumberFixer(e1);
		}

		this.setClauses(new Conjunction(p).getClauses());
	}
}
