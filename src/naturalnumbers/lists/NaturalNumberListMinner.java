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
import naturalnumbers.NaturalNumberArrayMinner;

/**
 * 
 *
 */
public class NaturalNumberListMinner extends Problem implements IProblem
{
	public NaturalNumberListMinner(INaturalNumberList d, INaturalNumber minEntry) throws Exception
	{
		this.setClauses(new NaturalNumberArrayMinner(d.getNaturalNumberArray(), minEntry).getClauses());
	}

	public NaturalNumberListMinner(INaturalNumberList d, INaturalNumber minIndex, INaturalNumber minEntry)
			throws Exception
	{
		this.setClauses(new NaturalNumberArrayMinner(d.getNaturalNumberArray(), minIndex, minEntry).getClauses());
	}
}
