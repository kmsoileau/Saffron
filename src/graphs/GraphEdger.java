package graphs;

import java.util.ArrayList;

import bits.BitFixer;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import graphs.generalized.exceptions.GraphEdgerException;
import naturalnumbers.NaturalNumberFixer;

public class GraphEdger extends Problem implements IProblem
{
	/**
	 * Satisfied if <code>predecessor</code> --&gt; <code>successor</code> in
	 * <code>graph</code>.
	 *
	 * @param graph       IGeneralizedGraph
	 * @param predecessor int
	 * @param successor   int
	 * @throws Exception _
	 */
	public GraphEdger(IGraph graph, INaturalNumber predecessor, INaturalNumber successor) throws Exception
	{
		if (graph == null)
			throw new GraphEdgerException("Null IGeneralizedGraph passed to constructor.");

		ArrayList<IProblem> p = new ArrayList<IProblem>();

		for (int i = 0; i < graph.size(); i++)
			for (int j = 0; j < graph.size(); j++)
			{
				IBooleanVariable valid = graph.getData(i, j);

				// if currP --&gt; currS then (predecessor == currP and
				// successor ==
				// currS)
				p.add(new Disjunction(new BitFixer(valid, false),
						new Conjunction(new NaturalNumberFixer(predecessor, i), new NaturalNumberFixer(successor, j))));
			}

		this.setClauses(new Disjunction(p).getClauses());
	}
}
