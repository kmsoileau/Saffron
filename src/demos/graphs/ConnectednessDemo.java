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
package demos.graphs;

import bits.BooleanLiteral;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import graphs.Connector;
import graphs.IUndirectedGraph;
import graphs.UndirectedGraph;

public class ConnectednessDemo
{
	public static void main(String[] args) throws Exception
	{
		int numberVertices = 8;

		IUndirectedGraph graph = new UndirectedGraph(numberVertices);
		graph.connect(0, 1);
		graph.connect(0, 3);
		graph.connect(1, 2);
		graph.connect(1, 4);
		graph.connect(5, 7);
		graph.connect(6, 5);
		graph.connect(6, 7);

		System.out.println(graph);

		IProblem problem = new Connector(graph);

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());

			System.out.println("Connected.");
		}
		else
			System.out.println("Disconnected.");
	}
}
