package graphs;

import java.util.ArrayList;

import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import graphs.exceptions.MapColorerException;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.NaturalNumberOrderer;
import naturalnumbers.NaturalNumberUnequalizer;

/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Jan 11, 2019
 */
public class MapColorer extends Problem implements IProblem
{
	public MapColorer(IUndirectedGraph skeleton, int numberOfColors,
			INaturalNumber[] coloring) throws Exception
	{
		if (skeleton == null)
			throw new MapColorerException("Null IGraph passed to constructor.");
		if (numberOfColors < 1)
			throw new MapColorerException("Bad int passed to constructor.");
		if (coloring == null)
			throw new MapColorerException(
					"Null INaturalNumber[] passed to constructor.");
		if (skeleton.size() != coloring.length)
			throw new MapColorerException(
					"In constructor, the length of the INaturalNumber[] does "
							+ "not match the size of the IGraph.");

		IProblem graphProblem = new GraphFixer(skeleton);

		for (int i = 0; i < skeleton.size(); i++)
			coloring[i] = new NaturalNumber("coloring-" + i);

		INaturalNumber pens = new NaturalNumber();
		IProblem pensFix = new NaturalNumberFixer(pens, numberOfColors - 1);

		IProblem[] pfix = new IProblem[skeleton.size()];
		for (int i = 0; i < skeleton.size(); i++)
		{
			pfix[i] = new NaturalNumberOrderer(coloring[i], pens);
		}
		IProblem paletteProblem = new Conjunction(pfix);

		ArrayList<IProblem> pList = new ArrayList<IProblem>();
		for (int i = 0; i < skeleton.size(); i++)
			for (int j = 0; j < skeleton.size(); j++)
			{
				if (i == j)
					continue;
				if (skeleton.areConnected(i, j))
				{
					pList.add(new NaturalNumberUnequalizer(coloring[i],
							coloring[j]));
				}
			}
		IProblem coloringProblem = new Conjunction(pList);

		IProblem problem = new Conjunction(graphProblem, pensFix,
				paletteProblem, coloringProblem);

		this.setClauses(problem.getClauses());
	}
}
