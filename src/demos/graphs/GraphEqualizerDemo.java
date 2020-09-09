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
package demos.graphs;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblemMessage;
import bits.Problem;
import graphs.GraphEqualizer;
import graphs.GraphFixer;
import graphs.IUndirectedGraph;
import graphs.UndirectedGraph;

/**
 * 
 *
 */
public class GraphEqualizerDemo
{
	public static void main(String[] args) throws Exception
	{
		int numberVertices = 8;

		IUndirectedGraph graph = new UndirectedGraph(numberVertices);
		graph.connect(0, 1);
		graph.connect(0, 3);
		graph.connect(1, 2);
		graph.connect(1, 4);
		graph.connect(2, 4);
		graph.connect(2, 5);
		graph.connect(3, 4);
		graph.connect(4, 6);
		graph.connect(5, 7);
		graph.connect(6, 5);
		graph.connect(6, 7);

		IUndirectedGraph graph2 = new UndirectedGraph(numberVertices);

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
