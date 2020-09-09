/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 28, 2019
 */
package demos.graphs;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import graphs.DirectedGraph;
import graphs.GraphFixer;
import graphs.GraphSquareRooter;
import graphs.IDirectedGraph;
import graphs.IGraph;

/**
 * 
 *
 */
public class SquareRooterDemo
{
	public static void main(String[] args) throws Exception
	{
		IDirectedGraph G = new DirectedGraph("G", 5);

		G.monoconnect(0, 1);
		G.monoconnect(0, 2);
		G.monoconnect(1, 2);
		G.monoconnect(1, 3);
		G.monoconnect(2, 3);
		G.monoconnect(2, 4);
		G.monoconnect(3, 4);

		IGraph gSquared = new DirectedGraph(G.size());

		IProblem problem = new Conjunction(new GraphFixer(G), new GraphSquareRooter(G, gSquared));

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println(G);
			System.out.println(gSquared);
		}
		else
			System.out.println("No solution.");
	}
}
