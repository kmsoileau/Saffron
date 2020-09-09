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
package demos.graphs;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import graphs.DirectedGraph;
import graphs.GraphAsProblem;
import graphs.IDirectedGraph;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;

/**
 * 
 *
 */
public class Demo extends Problem implements IProblem
{
	public static void main(String[] args) throws Exception
	{
		int numberOfVertices = 8;

		IDirectedGraph graph = new DirectedGraph(numberOfVertices);
		graph.monoconnect(0, 1);
		graph.monoconnect(0, 3);
		graph.monoconnect(1, 1);
		graph.monoconnect(1, 2);
		graph.monoconnect(1, 4);
		graph.biconnect(2, 4);
		graph.monoconnect(2, 5);
		graph.monoconnect(3, 4);
		graph.monoconnect(4, 6);
		graph.monoconnect(5, 7);
		graph.monoconnect(6, 5);
		graph.monoconnect(6, 7);

		System.out.println(graph.toString() + "\n");

		for (int startVertex = 0; startVertex < numberOfVertices; startVertex++)
			for (int endVertex = 0; endVertex < numberOfVertices; endVertex++)
			{
				INaturalNumber StartVertex = new NaturalNumber("start", startVertex);
				INaturalNumber EndVertex = new NaturalNumber("end", endVertex);

				IProblem startFix = new NaturalNumberFixer(StartVertex);
				IProblem endFix = new NaturalNumberFixer(EndVertex);
				IProblem graphProb = new GraphAsProblem(graph, StartVertex, EndVertex);

				IProblem problem = new Conjunction(startFix, endFix, graphProb);

				IProblemMessage s = problem.findModel(Problem.defaultSolver());

				if (s.getStatus() == IProblemMessage.SATISFIABLE)
				{
					BooleanLiteral.interpret(s.getLiterals());
					System.out.println(startVertex + "->" + endVertex);
				}
			}
	}
}
