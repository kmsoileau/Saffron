package graphs;

import java.util.ArrayList;

import bits.Conjunction;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Mapper;
import bits.Problem;
import bits.ProblemPair;
import graphs.exceptions.GraphAsProblemException;
import naturalnumbers.NaturalNumberFixer;

/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Jan 17, 2019
 */
public class GraphAsProblem extends Problem implements IProblem
{
	public GraphAsProblem(IGraph g, INaturalNumber startVertex,
			INaturalNumber endVertex) throws Exception
	{
		if (g == null)
			throw new GraphAsProblemException(
					"Null IGraph passed to constructor.");
		if (startVertex == null || endVertex == null)
			throw new GraphAsProblemException(
					"Null INaturalNumber passed to constructor.");
		IBooleanVariable[][] data = g.getData();
		ArrayList<ProblemPair> list = new ArrayList<ProblemPair>();
		for (int i = 0; i < g.size(); i++)
		{
			IProblem svfix = new NaturalNumberFixer(startVertex, i);
			for (int j = 0; j < g.size(); j++)
			{
				IProblem evfix = new NaturalNumberFixer(endVertex, j);
				if (data[i][j].getValue())
				{
					list.add(new ProblemPair(svfix, evfix));
				}
			}
		}
		IProblem graphProb = new Mapper(list);
		this.setClauses(graphProb.getClauses());
	}

	public GraphAsProblem(IGraph g, INaturalNumber[] vertices) throws Exception
	{
		if (g == null)
			throw new GraphAsProblemException(
					"Null IGraph passed to constructor.");
		if (vertices == null)
			throw new GraphAsProblemException(
					"Null INaturalNumber array passed to constructor.");

		IProblem[] graphProb = new IProblem[vertices.length - 1];
		for (int i = 0; i < vertices.length - 1; i++)
		{
			graphProb[i] = new GraphAsProblem(g, vertices[i], vertices[i + 1]);
		}

		this.setClauses(new Conjunction(graphProb).getClauses());
	}
}
