package graphs;

import java.util.ArrayList;

import bits.BitArraySingleSetter;
import bits.BitFixer;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;

/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 2, 2019
 */

public class SubPather extends Problem implements IProblem
{
	public SubPather(IGraph G, IBooleanVariable[] membership) throws Exception
	{
		IBooleanVariable[] isLast = new IBooleanVariable[G.size()];
		for (int i = 0; i < G.size(); i++)
		{
			isLast[i] = BooleanVariable.getBooleanVariable();
		}

		ArrayList<IProblem> q = new ArrayList<IProblem>();
		for (int i = 0; i < G.size(); i++)
		{
			ArrayList<IProblem> p = new ArrayList<IProblem>();
			for (int j = 0; j < G.size(); j++)
			{
				p.add(new Conjunction(new BitFixer(G.getData(i, j), true),
						new BitFixer(membership[j], true)));
			}

			q.add(new Disjunction(new Disjunction(new BitFixer(membership[i],
					false), new BitFixer(isLast[i], true)), new Disjunction(p)));
		}

		this.setClauses(new Conjunction(new BitArraySingleSetter(isLast),
				new Conjunction(q)).getClauses());
	}
}
