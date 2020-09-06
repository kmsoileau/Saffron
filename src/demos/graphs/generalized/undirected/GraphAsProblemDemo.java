/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 26, 2019
 */
package demos.graphs.generalized.undirected;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

/**
 * 
 *
 */
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

import graphs.generalized.DirectedGraph;
import graphs.generalized.GraphAsProblem;
import graphs.generalized.GraphFixer;
import graphs.generalized.IDirectedGraph;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;

/**
* 
*
*/
public class GraphAsProblemDemo extends Problem implements IProblem
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

		System.out.println(graph.toString() + "\n");

		int numberOfVertices = graph.getVertices().entrySet().size();

		for (int startVertex = 0; startVertex < numberOfVertices; startVertex++)
			for (int endVertex = 0; endVertex < numberOfVertices; endVertex++)
			{
				INaturalNumber StartVertex = new NaturalNumber("start",
						startVertex);
				INaturalNumber EndVertex = new NaturalNumber("end", endVertex);

				IProblem graphFix = new GraphFixer(graph);
				IProblem startFix = new NaturalNumberFixer(StartVertex);
				IProblem endFix = new NaturalNumberFixer(EndVertex);
				IProblem graphProb = new GraphAsProblem(graph, StartVertex,
						EndVertex);

				IProblem problem = new Conjunction(graphFix, startFix, endFix,
						graphProb);

				IProblemMessage s = problem.findModel(Problem.defaultSolver());

				if (s.getStatus() == IProblemMessage.SATISFIABLE)
				{
					BooleanLiteral.interpret(s.getLiterals());
					System.out.println(startVertex + "->" + endVertex);
				}
			}
	}
}