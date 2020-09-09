package graphs;

import java.util.ArrayList;

import bits.BitFixer;
import bits.Conjunction;
import bits.Disjunction;
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
 * @since Mar 1, 2019
 */
public class GraphMerger extends Problem implements IProblem
{
	public GraphMerger(IGraph g, IGraph G2, IGraph gSquared) throws Exception
	{
		ArrayList<IProblem> p = new ArrayList<IProblem>();
		for (int i = 0; i < g.size(); i++)
			for (int j = 0; j < g.size(); j++)
			{
				// if new BitFixer(G2.getData(i, j), true) then new
				// BitFixer(G.getData(i, j), true)
				p.add(new Disjunction(new BitFixer(g.getData(i, j), false),
						new BitFixer(gSquared.getData(i, j), true)));
				p.add(new Disjunction(new BitFixer(G2.getData(i, j), false),
						new BitFixer(gSquared.getData(i, j), true)));
				p.add(new Disjunction(new BitFixer(g.getData(i, j), true), new BitFixer(G2.getData(i, j), true),
						new BitFixer(gSquared.getData(i, j), false)));
			}

		this.setClauses(new Conjunction(p).getClauses());
	}
}
