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
import naturalnumbers.NaturalNumberAdder;
import naturalnumbers.NaturalNumberEqualizer;

/**
 * 
 *
 */
public class NaturalNumberListTotaler extends Problem implements IProblem
{
	public NaturalNumberListTotaler(INaturalNumberList list, INaturalNumber total) throws Exception
	{
		if (list.size() == 1)
		{
			this.setClauses(new NaturalNumberEqualizer(total, list.getNaturalNumber(0)).getClauses());
		}
		if (list.size() > 1)
		{
			INaturalNumberList subTotal = new NaturalNumberList(list.size() - 1);

			ArrayList<IProblem> p = new ArrayList<IProblem>();
			p.add(new NaturalNumberEqualizer(subTotal.getNaturalNumber(0), list.getNaturalNumber(0)));
			for (int i = 1; i < list.size() - 1; i++)
			{
				p.add(new NaturalNumberAdder(subTotal.getNaturalNumber(i - 1), list.getNaturalNumber(i),
						subTotal.getNaturalNumber(i)));
			}

			p.add(new NaturalNumberAdder(subTotal.getNaturalNumber(list.size() - 2),
					list.getNaturalNumber(list.size() - 1), total));

			this.setClauses(new Conjunction(p).getClauses());
		}
	}
}
