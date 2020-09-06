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
import java.util.Set;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import graphs.generalized.DirectedGraph;
import graphs.generalized.IDirectedGraph;
import naturalnumbers.NNtoNNMapper;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberEqualizer;
import naturalnumbers.NaturalNumberFixer;

/**
 * 
 *
 */
public class Demo3 extends Problem implements IProblem
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

		HashMap<INaturalNumber, HashMap<INaturalNumber, IBooleanVariable>> d = graph
				.getData();
		Set<INaturalNumber> ks = d.keySet();
		for (INaturalNumber nn : ks)
			System.out.println(nn.getName());

		IProblem nnn = graph.getBacking();

		INaturalNumber theX = ((NNtoNNMapper) nnn).getX();
		INaturalNumber theY = ((NNtoNNMapper) nnn).getY();
		INaturalNumber first = new NaturalNumber(5);
		INaturalNumber second = new NaturalNumber(7);

		IProblem p1 = new NaturalNumberEqualizer(first, theX);
		IProblem p2 = new NaturalNumberEqualizer(second, theY);
		IProblem p3 = new NaturalNumberFixer(first);
		IProblem p4 = new NaturalNumberFixer(second);

		IProblem problem = new Conjunction(nnn, p1, p2, p3, p4);

		IProblemMessage s = problem.findModel(Problem.defaultSolver());

		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("Satisfied.");
			System.out.println("theX = "+theX.getName());
			System.out.println("theY = "+theY.getName());
			System.out.println("first = "+first.getName());
			System.out.println("second = "+second.getName());
		}
		else
			System.out.println("Unsatisfied.");
	}
}
