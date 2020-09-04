package showcase.vertexcover;

import bits.BooleanLiteral;
import bits.IBitString;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;
import graphs.DirectedGraph;
import graphs.IDirectedGraph;
import graphs.VertexCoverer;

public class VertexCoverDemo
{
	public static void main(String[] args) throws Exception
	{
		/**
		 * Set Java variables:
		 */

		int V = 6; // The number of vertices
		int K = 2; // The number of vertices in the cover

		/**
		 * Set globals:
		 */

		/**
		 * Create Saffron objects and arrays:
		 */

		IDirectedGraph G = new DirectedGraph(V);
		G.biconnect(0, 2);
		G.biconnect(1, 2);
		G.biconnect(2, 3);
		G.biconnect(3, 4);
		G.biconnect(3, 5);

		IBitString cover = new BitString(V);

		/**
		 * Create problems which constrain the values of these Saffron objects:
		 */

		/**
		 * Create the IProblem of satisfying all of these constraining problems:
		 */

		IProblem problem = new VertexCoverer(G, K, cover);

		/**
		 * Solve the IProblem:
		 */

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.print("GRAPH\n" + G);
			System.out.print("\n\nSOLUTION\nCover Vertices:");
			for (int i = 0; i < cover.size(); i++)
				if (cover.getBooleanVariable(i).getValue())
					System.out.print(" " + i);
		}
		else
			System.out.println("No solution.");
	}
}