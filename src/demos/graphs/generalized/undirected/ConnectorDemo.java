/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 18, 2019
 */
package demos.graphs.generalized.undirected;

import bits.BooleanLiteral;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import graphs.Connector;
import graphs.IUndirectedGraph;
import graphs.UndirectedGraph;

/**
 * C_n=C(B_n) B_{n+1}=C_n \ G_n G_{n+1}=G_n U B_{n+1}
 *
 * ? C_n=C(C_{n-1} \ G_{n-1}) ? G_{n+1}=G_n U (C_n \ G_n)
 * 
 * Note that in a UndirectedGraph, every vertex is connected to itself. Thus the
 * graph constructed with the following code:
 * 
 * IUndirectedGraph graph = new UndirectedGraph(numberVertices);
 * graph.connect(0, 1); graph.connect(0, 3);
 * 
 * is *not* the connected graph {0--1,0--3}, but the disconnected graph
 * {0--0,0--1,0--3,1--1,2--2,3--3}. The 2 appears because the constructor
 * creates the vertices (0,1,2,...,numberVertices-1}.
 */
public class ConnectorDemo
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