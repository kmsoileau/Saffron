/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Jan 19, 2019
 */
package demos.graphs;

import bits.BitFixer;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.RangeSubset;
import graphs.DirectedGraph;
import graphs.GraphFixer;
import graphs.GraphVertexSubset;
import graphs.IDirectedGraph;

/**
 * 
 *
 */
public class VertexSubsetDemo
{
	public static void main(String[] args) throws Exception
	{
		IDirectedGraph G = new DirectedGraph(6);
		G.biconnect(0, 2);
		G.biconnect(1, 2);
		G.biconnect(2, 3);
		G.biconnect(3, 4);
		G.biconnect(3, 5);

		System.out.println(G);

		RangeSubset membership = new RangeSubset(G.size());

		GraphVertexSubset bsgv = new GraphVertexSubset(G, membership);

		IProblemMessage s = new Conjunction(new GraphFixer(G), new BitFixer(
				membership.getBV(2), true), new BitFixer(membership.getBV(4),
				true)).findModel(Problem.defaultSolver());

		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println(bsgv);
		}
		else
			System.out.println("No solution.");
	}
}
