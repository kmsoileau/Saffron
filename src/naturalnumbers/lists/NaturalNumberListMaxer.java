/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 25, 2019
 */
package naturalnumbers.lists;

import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.NaturalNumberArrayMaxer;

/**
 * 
 *
 */
public class NaturalNumberListMaxer extends Problem implements IProblem
{
	public NaturalNumberListMaxer(INaturalNumberList d, INaturalNumber maxEntry)
			throws Exception
	{
		this.setClauses(new NaturalNumberArrayMaxer(d.getNaturalNumberArray(),
				maxEntry).getClauses());
	}

	public NaturalNumberListMaxer(INaturalNumberList d,
			INaturalNumber maxIndex, INaturalNumber maxEntry) throws Exception
	{
		this.setClauses(new NaturalNumberArrayMaxer(d.getNaturalNumberArray(),
				maxIndex, maxEntry).getClauses());
	}
}
