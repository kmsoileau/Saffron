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
package demos.graphs.generalized.directed;

import java.util.HashMap;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import graphs.generalized.DirectedGraph;
import graphs.generalized.Graph;
import graphs.generalized.GraphFixer;
import graphs.generalized.IDirectedGraph;

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

		IDirectedGraph graph = new DirectedGraph("G", data);

		System.out.println(graph.getLookup());

		Graph.setStrLabel("DirectedGeneralizedGraph");

		HashMap<Integer, INaturalNumber> verts = graph.getVertices();
		System.out.println("vertices = " + verts);

		IProblem fixProblem = new GraphFixer(graph);

		IProblem problem = new Conjunction(fixProblem);

		IProblemMessage s = problem.findModel(Problem.defaultSolver());

		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println(graph);
		}
	}
}
