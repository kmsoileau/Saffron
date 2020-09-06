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
package graphs;

import java.util.ArrayList;

import bits.BitEqualizer;
import bits.Conjunction;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;

/**
 * 
 *
 */
public class GraphEqualizer extends Problem implements IProblem
{
	public GraphEqualizer(IGraph g1, IGraph g2) throws Exception
	{
		IBooleanVariable[][] data1 = g1.getData();
		IBooleanVariable[][] data2 = g2.getData();

		ArrayList<IProblem> p = new ArrayList<IProblem>();
		for (int i = 0; i < data1.length; i++)
		{
			IBooleanVariable[] curr1 = data1[i];
			IBooleanVariable[] curr2 = data2[i];
			for (int j = 0; j < curr1.length; j++)
			{
				p.add(new BitEqualizer(curr1[j], curr2[j]));
			}
		}

		this.setClauses(new Conjunction(p).getClauses());
	}
}
