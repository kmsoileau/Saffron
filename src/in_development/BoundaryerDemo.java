/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 5, 2019
 */
package in_development;

import java.util.ArrayList;
import java.util.HashMap;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import graphs.generalized.Graph;
import graphs.generalized.GraphFixer;
import graphs.generalized.IGraph;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberPair;

/**
 * 
 *
 */
public class BoundaryerDemo
{
	static HashMap<Integer, INaturalNumber> hashMap(int[][] data)
			throws Exception
	{
		HashMap<Integer, INaturalNumber> nnMap = new HashMap<Integer, INaturalNumber>();
		for (int[] curr : data)
		{
			int first = curr[0];
			int second = curr[1];
			if (nnMap.get(first) == null)
				nnMap.put(first, new NaturalNumber(first));
			if (nnMap.get(second) == null)
				nnMap.put(second, new NaturalNumber(second));
		}
		return nnMap;
	}

	public static void main(String[] args) throws Exception
	{
		int[][] data = new int[][]
		{
		{ 0, 1 },
		{ 0, 3 },
		{ 1, 1 },
		{ 1, 2 },
		{ 1, 4 },
		{ 2, 4 },
		{ 2, 5 },
		{ 3, 4 },
		{ 4, 6 },
		{ 5, 7 },
		{ 6, 5 },
		{ 6, 7 } };

		HashMap<Integer, INaturalNumber> nnMap = hashMap(data);

		ArrayList<NaturalNumberPair> pairs = new ArrayList<NaturalNumberPair>();
		for (int i = 0; i < data.length; i++)
			pairs.add(new NaturalNumberPair(nnMap.get(data[i][0]), nnMap
					.get(data[i][1])));

		IGraph graph = new Graph(data);

		NaturalNumber[] vertices = nnMap.values().toArray(new NaturalNumber[0]);

		IProblem fixer = new GraphFixer(graph);

		IProblem problem = new Conjunction(fixer);

		HashMap<INaturalNumber, Boolean> G0 = new HashMap<INaturalNumber, Boolean>();

		HashMap<INaturalNumber, Boolean> B0 = new HashMap<INaturalNumber, Boolean>();

		NaturalNumber start = vertices[1];
		G0.put(start, true);
		B0.put(start, true);

		HashMap<INaturalNumber, Boolean> B1 = new HashMap<INaturalNumber, Boolean>();
		ArrayList<NaturalNumberPair> prs = graph.getPairs();
		for (INaturalNumber curr : vertices)
		{
			Boolean qq = B0.get(curr);
			if (qq != null && qq)
			{
				for (int i = 0; i < graph.size(); i++)
				{
					INaturalNumber first = prs.get(i).getFirst();
					INaturalNumber second = prs.get(i).getSecond();
					if (first == curr && second != curr)
						B1.put(second, true);
				}
			}
		}

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println(graph);
		}
		else
			System.out.println("No solution.");
	}
}
