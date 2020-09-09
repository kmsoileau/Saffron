/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 9, 2019
 */
package demos.graphs.generalized.directed;

import java.util.HashMap;
import java.util.Set;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBooleanVariable;
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
public class PredecessorDemo extends Problem implements IProblem
{
	public static void main(String[] args) throws Exception
	{
		int[][] data = new int[][]
		{
				{ 0, 1 },
				{ 0, 2 },
				{ 1, 2 },
				{ 2, 3 },
				{ 2, 4 },
				{ 3, 4 } };

		IDirectedGraph graph = new DirectedGraph("G", data);

		System.out.println("graph = " + graph);

		for (INaturalNumber curr : graph.getVertices().values())
		{
			System.out.println(
					"vertex " + curr.getName() + " has value " + curr.getValue() + " and hashCode " + curr.hashCode());
		}

		HashMap<INaturalNumber, HashMap<INaturalNumber, IBooleanVariable>> lu = graph.getLookup();
		for (INaturalNumber curr : lu.keySet())
		{
			Set<INaturalNumber> ks = lu.get(curr).keySet();
			for (INaturalNumber curr2 : ks)
			{
				System.out.println("(" + curr + "," + curr2 + ")=(" + curr.getName() + "," + curr2.getName() + ")-->"
						+ lu.get(curr).get(curr2));
			}
		}

		INaturalNumber pred = new NaturalNumber(2);
		INaturalNumber succ = new NaturalNumber(4);
		IProblem problem = new Conjunction(new GraphFixer(graph), new NaturalNumberFixer(pred),
				new NaturalNumberFixer(succ), new GraphEdger(graph, pred, succ));

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("pred = " + pred);
			System.out.println("succ = " + succ);
		}
		else
			System.out.print("No solution.");
	}
}
