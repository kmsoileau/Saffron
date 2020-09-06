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
package algebra.magmas.generalized;

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
public class MagmaFixer extends Problem implements IProblem
{
	public MagmaFixer(Magma mgm) throws Exception
	{
		// Fixes all of the INaturalNumbers in the
		// mgm.getComposition(),
		// these are assumed to be all of the elements in the semigroup.
		HashMap<INaturalNumber, HashMap<INaturalNumber, INaturalNumber>> hm1 = mgm
				.getComposition();

		int index = 0;
		int order=mgm.getOrder();
		IProblem[] p = new IProblem[order*order+order];
		for (INaturalNumber e1 : hm1.keySet()) //order
		{
			p[index++] = new NaturalNumberFixer(e1);
			HashMap<INaturalNumber, INaturalNumber> curr = hm1.get(e1);
			for (INaturalNumber e2 : curr.keySet())
			{
				p[index++] = new Conjunction(new NaturalNumberFixer(e2),
						new NaturalNumberFixer(curr.get(e2)));
			}
		}

		this.setClauses(new Conjunction(p).getClauses());
	}
}
