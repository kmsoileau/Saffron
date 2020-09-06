/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 15, 2019
 */
package demos.graphs.generalized.directed;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import graphs.generalized.DirectedGraph;
import graphs.generalized.GraphEdger;
import graphs.generalized.GraphFixer;
import graphs.generalized.IDirectedGraph;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;

/**
 * 
 *
 */

public class EdgerDemo2
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
		{ 3, 7 },
		{ 4, 6 },
		{ 5, 7 },
		{ 6, 5 },
		{ 6, 7 } };

		IDirectedGraph graph = new DirectedGraph("G",
				data);

		System.out.println(graph);

		INaturalNumber predecessor = new NaturalNumber(3);
		INaturalNumber successor = new NaturalNumber();

		IProblem problem = new Conjunction(new GraphFixer(graph),
				new NaturalNumberFixer(predecessor), new GraphEdger(graph,
						predecessor, successor));

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("graph = " + graph);

			System.out.println("predecessor = " + predecessor);
			System.out.println("successor = " + successor);
		}
		else
			System.out.print("No solution.");
	}
}
