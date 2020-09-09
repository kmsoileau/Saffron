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
package demos.graphs.generalized.undirected;

import java.util.ArrayList;

import bits.BooleanLiteral;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import graphs.generalized.GraphFixer;
import graphs.generalized.UndirectedGraph;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberPair;

/**
 * 
 *
 */
public class Demo extends Problem implements IProblem
{
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

		ArrayList<NaturalNumberPair> pairs = new ArrayList<NaturalNumberPair>();
		for (int i = 0; i < data.length; i++)
		{
			if (data[i][0] != data[i][1])
			{
				pairs.add(new NaturalNumberPair(new NaturalNumber(data[i][0]), new NaturalNumber(data[i][1])));
				pairs.add(new NaturalNumberPair(new NaturalNumber(data[i][1]), new NaturalNumber(data[i][0])));
			}
			else
			{
				pairs.add(new NaturalNumberPair(new NaturalNumber(data[i][0]), new NaturalNumber(data[i][1])));
			}
		}

		UndirectedGraph graph = new UndirectedGraph(data);

		IProblem problem = new GraphFixer(graph);

		IProblemMessage s = problem.findModel(Problem.defaultSolver());

		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println(graph);
		}
	}
}
