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
package showcase.graphpathfinder;

import naturalnumbers.NaturalNumber;
import bits.BooleanLiteral;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bitstringgraphs.BitStringGraph;
import bitstringgraphs.IBitStringGraph;
import bitstringgraphs.PathFinder;

public class PathFinderDemo
{
	public static void main(String[] args) throws Exception
	{
		/**
		 * Set Java variables:
		 */

		int numberOfVertices = 8;

		int startVertex = 3;
		int endVertex = 6;

		int K = 9;

		/**
		 * Set globals:
		 */

		/**
		 * Create Saffron objects and arrays:
		 */

		// IBitStringGraph G = new BitStringGraph(numberOfVertices);
		// G.monoconnect(0, 1);
		// G.monoconnect(0, 3);
		// G.monoconnect(1, 1);
		// G.monoconnect(1, 2);
		// G.monoconnect(1, 4);
		// G.biconnect(2, 4);
		// G.monoconnect(2, 5);
		// G.monoconnect(3, 4);
		// G.monoconnect(4, 6);
		// G.monoconnect(5, 7);
		// G.monoconnect(6, 5);
		// G.monoconnect(6, 7);

		IBitStringGraph G = new BitStringGraph(numberOfVertices);
		G.monoconnect(0, 1);
		G.monoconnect(1, 2);
		G.monoconnect(2, 3);
		G.monoconnect(3, 4);
		G.monoconnect(4, 5);
		G.monoconnect(5, 6);
		G.monoconnect(6, 6);
		G.monoconnect(6, 7);
		G.monoconnect(7, 4);

		INaturalNumber[] vertex = new NaturalNumber[K + 1];
		for (int i = 0; i <= K; i++)
			vertex[i] = new NaturalNumber();

		/**
		 * Create problems which constrain the values of these Saffron objects:
		 */

		/**
		 * Create the IProblem of satisfying all of these constraining problems:
		 */

		IProblem problem = new PathFinder(G, startVertex, endVertex, K, vertex);

		/**
		 * Solve the IProblem:
		 */

		IProblemMessage s = problem.findModel(Problem.defaultSolver());

		if (s.getStatus() == IProblemMessage.SATISFIABLE
				&& s.getLiterals().size() > 0)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.print("GRAPH\n");
			System.out.println(G.toString() + "\n");
			System.out.print("SOLUTION\n");
			System.out.print(vertex[0]);
			for (int i = 1; i < vertex.length; i++)
				System.out.print("->" + vertex[i]);
		}
	}
}
