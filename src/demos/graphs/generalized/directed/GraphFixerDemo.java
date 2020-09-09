/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 24, 2019
 */
package demos.graphs.generalized.directed;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblemMessage;
import bits.Problem;
import graphs.generalized.DirectedGraph;
import graphs.generalized.GraphEqualizer;
import graphs.generalized.GraphFixer;
import graphs.generalized.IGraph;

/**
 * 
 *
 */
public class GraphFixerDemo
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
				{ 3, 7 },
				{ 4, 6 },
				{ 5, 7 },
				{ 6, 5 },
				{ 6, 7 } };

		IGraph graph = new DirectedGraph("G", data);

		IGraph graph2 = new DirectedGraph(data);

		IProblemMessage s = new Conjunction(new GraphFixer(graph), new GraphEqualizer(graph, graph2))
				.findModel(Problem.defaultSolver());

		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println(graph);
			System.out.println(graph2);
		}
		else
			System.out.println("No solution.");

	}

}
