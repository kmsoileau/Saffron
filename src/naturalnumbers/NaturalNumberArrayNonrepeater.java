/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 22, 2019
 */
package naturalnumbers;

import bits.Conjunction;
import bits.IClause;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

/**
 * 
 *
 */
public class NaturalNumberArrayNonrepeater extends Problem implements IProblem
{
	public NaturalNumberArrayNonrepeater(INaturalNumber[] array) throws Exception
	{
		IProblem p = null;
		for (int i = 0; i < array.length; i++)
		{
			INaturalNumber b = array[i];
			for (int j = i + 1; j < array.length; j++)
				p = new Conjunction(p, new NaturalNumberUnequalizer(b, array[j]));
		}
		if (p == null)
			this.setClauses((IClause[]) null);
		else
			this.setClauses(p.getClauses());
	}
}
