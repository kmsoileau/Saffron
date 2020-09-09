/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 11, 2019
 */
package demos.graphs;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.BitStringFixer;
import graphs.GraphFixer;
import graphs.IUndirectedGraph;
import graphs.Neighborer;
import graphs.UndirectedGraph;

/**
 * 
 *
 */
public class NeighborerDemo
{
	public static void main(String[] args) throws Exception
	{
		int numberVertices = 8;

		IUndirectedGraph graph = new UndirectedGraph(numberVertices);
		graph.connect(0, 1);
		graph.connect(1, 2);
		graph.connect(2, 3);
		graph.connect(1, 2);
		graph.connect(1, 4);
		graph.connect(2, 4);
		graph.connect(2, 5);
		graph.connect(3, 4);
		graph.connect(4, 6);
		graph.connect(5, 7);
		graph.connect(6, 5);
		graph.connect(6, 7);
		graph.connect(7, 7);

		IBitString homes = new BitString("01000000");

		IBitString neighbors = new BitString(numberVertices);

		IProblem problem = new Conjunction(new GraphFixer(graph), new BitStringFixer(homes),
				new Neighborer(graph, homes, neighbors));

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("graph = " + graph);

			System.out.println("neighbors = " + neighbors.toBits());

		}
		else
			System.out.println("No solution.");
	}
}
