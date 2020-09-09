/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 9, 2019
 */
package graphs.generalized;

import java.util.ArrayList;
import java.util.HashMap;

import bits.BitEqualizer;
import bits.Conjunction;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

/**
 * 
 *
 */
public class GraphEqualizer extends Problem implements IProblem
{
	@SuppressWarnings("unlikely-arg-type")
	public GraphEqualizer(IGraph g1, IGraph g2) throws Exception
	{
		HashMap<INaturalNumber, HashMap<INaturalNumber, IBooleanVariable>> data1 = g1.getData();
		HashMap<INaturalNumber, HashMap<INaturalNumber, IBooleanVariable>> data2 = g2.getData();

		ArrayList<IProblem> p = new ArrayList<IProblem>();
		for (int i = 0; i < data1.size(); i++)
		{
			HashMap<INaturalNumber, IBooleanVariable> curr1 = data1.get(i);
			HashMap<INaturalNumber, IBooleanVariable> curr2 = data2.get(i);
			if (curr1 != null & curr2 != null)
				for (int j = 0; j < curr1.size(); j++)
				{
					p.add(new BitEqualizer(curr1.get(j), curr2.get(j)));
				}
		}

		this.setClauses(new Conjunction(p).getClauses());
	}
}
