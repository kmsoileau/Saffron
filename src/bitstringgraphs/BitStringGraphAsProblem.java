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
package bitstringgraphs;

import java.util.ArrayList;

import naturalnumbers.NaturalNumberFixer;
import bits.Conjunction;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Mapper;
import bits.Problem;
import bits.ProblemPair;
import exceptions.bitstringgraphs.BitStringGraphAsProblemException;

public class BitStringGraphAsProblem extends Problem implements IProblem
{
	public BitStringGraphAsProblem(IBitStringGraph graph,
			INaturalNumber startVertex, INaturalNumber endVertex)
			throws Exception
	{
		if (graph == null)
			throw new BitStringGraphAsProblemException(
					"Null IBitStringGraph passed to constructor.");
		if (startVertex == null || endVertex == null)
			throw new BitStringGraphAsProblemException(
					"Null INaturalNumber passed to constructor.");
		IBooleanVariable[][] data = graph.getData();
		ArrayList<ProblemPair> list = new ArrayList<ProblemPair>();
		for (int i = 0; i < graph.size(); i++)
		{
			IProblem svfix = new NaturalNumberFixer(startVertex, i);
			for (int j = 0; j < graph.size(); j++)
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

	public BitStringGraphAsProblem(IBitStringGraph graph,
			INaturalNumber[] vertices) throws Exception
	{
		if (graph == null)
			throw new BitStringGraphAsProblemException(
					"Null IBitStringGraph passed to constructor.");
		if (vertices == null)
			throw new BitStringGraphAsProblemException(
					"Null INaturalNumber array passed to constructor.");

		IProblem[] graphProb = new IProblem[vertices.length - 1];
		for (int i = 0; i < vertices.length - 1; i++)
		{
			graphProb[i] = new BitStringGraphAsProblem(graph, vertices[i],
					vertices[i + 1]);
		}

		this.setClauses(new Conjunction(graphProb).getClauses());
	}
}
