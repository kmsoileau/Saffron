package graphs;

import java.util.ArrayList;

import bits.BitFixer;
import bits.Conjunction;
import bits.Disjunction;
import bits.IProblem;
import bits.Problem;

class BiPather extends Problem implements IProblem
{
	public BiPather(IGraph g, int start, int finish) throws Exception
	{
		ArrayList<IProblem> p = new ArrayList<IProblem>();
		for (int link = 0; link < g.size(); link++)
		{
			if (start != link && link != finish)
			{
				p.add(new BiPather(g, start, link, finish));
			}
		}

		this.setClauses(new Disjunction(p).getClauses());
	}

	public BiPather(IGraph g, int start, int link, int finish) throws Exception
	{
		this.setClauses(new Conjunction(new BitFixer(g.getData(start, link),
				true), new BitFixer(g.getData(link, finish), true))
				.getClauses());
	}
}

/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 28, 2019
 */
public class GraphSquarer extends Problem implements IProblem
{
	public GraphSquarer(IGraph g, IGraph gSquared) throws Exception
	{
		IGraph G2 = new DirectedGraph("G2", g.size());

		ArrayList<IProblem> p = new ArrayList<IProblem>();
		for (int i = 0; i < g.size(); i++)
			for (int j = 0; j < g.size(); j++)
			{
				// G2.getData(i, j)==true => BiPath(G, i, j)
				p.add(new Conjunction(new Disjunction(new BitFixer(G2.getData(
						i, j), false), new BiPather(g, i, j)), new Disjunction(
						new BitFixer(G2.getData(i, j), true), new NoBiPather(g,
								i, j))));
			}

		this.setClauses(new Conjunction(new GraphMerger(g, G2, gSquared),
				new Conjunction(p)).getClauses());
	}
}

class NoBiPather extends Problem implements IProblem
{
	public NoBiPather(IGraph g, int start, int finish) throws Exception
	{
		ArrayList<IProblem> p = new ArrayList<IProblem>();
		for (int link = 0; link < g.size(); link++)
		{
			if (start != link && link != finish)
			{
				p.add(new NoBiPather(g, start, link, finish));
			}
		}

		this.setClauses(new Conjunction(p).getClauses());
	}

	public NoBiPather(IGraph g, int start, int link, int finish)
			throws Exception
	{
		this.setClauses(new Disjunction(new BitFixer(g.getData(start, link),
				false), new BitFixer(g.getData(link, finish), false))
				.getClauses());
	}
}
