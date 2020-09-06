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
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import graphs.Graph;
import graphs.GraphFixer;
import graphs.IGraph;

/**
 * 
 *
 */
public class FixerDemo extends Problem implements IProblem
{
	public static void main(String[] args) throws Exception
	{
		int numberOfVertices = 8;

		IGraph graph = new Graph(numberOfVertices);
		graph.connect(0, 1);
		graph.connect(0, 3);
		graph.connect(1, 1);
		graph.connect(1, 2);
		graph.connect(1, 4);
		graph.connect(2, 4);
		graph.connect(2, 5);
		graph.connect(3, 4);
		graph.connect(4, 6);
		graph.connect(5, 7);
		graph.connect(6, 5);
		graph.connect(6, 7);

		IProblemMessage s = new GraphFixer(graph).findModel(Problem
				.defaultSolver());

		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println(graph + "\n");
		}
	}
}
