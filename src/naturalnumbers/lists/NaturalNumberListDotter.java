/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 20, 2019
 */
package naturalnumbers.lists;

import java.util.ArrayList;

import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.NaturalNumberMultiplier;

public class NaturalNumberListDotter extends Problem implements IProblem
{
	public NaturalNumberListDotter(INaturalNumberList list1, INaturalNumberList list2, INaturalNumber dotProduct)
			throws Exception
	{
		INaturalNumberList subProduct = new NaturalNumberList(list1.size());
		ArrayList<IProblem> p = new ArrayList<IProblem>();
		for (int i = 0; i < list1.size(); i++)
		{
			p.add(new NaturalNumberMultiplier(list1.getNaturalNumber(i), list2.getNaturalNumber(i),
					subProduct.getNaturalNumber(i)));
		}

		IProblem problem = new Conjunction(new Conjunction(p), new NaturalNumberListTotaler(subProduct, dotProduct));

		this.setClauses(problem.getClauses());
	}
}