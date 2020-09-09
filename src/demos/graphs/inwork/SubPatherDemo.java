package demos.graphs.inwork;

import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import graphs.DirectedGraph;
import graphs.GraphFixer;
import graphs.IDirectedGraph;
import graphs.SubPather;
import naturalnumbers.BitArrayTotaler;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;

/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 2, 2019
 */
public class SubPatherDemo
{
	public static void main(String[] args) throws Exception
	{
		IDirectedGraph G = new DirectedGraph("G", 8);
		// G.monoconnect(0, 1);
		// G.monoconnect(1, 2);
		// G.monoconnect(2, 3);
		// G.monoconnect(2, 4);
		// G.monoconnect(3, 4);
		// G.monoconnect(3, 6);
		// G.monoconnect(4, 0);
		// G.monoconnect(4, 7);

		G.monoconnect(2, 1);
		G.monoconnect(3, 1);
		G.monoconnect(4, 1);
		G.monoconnect(1, 5);
		G.monoconnect(5, 6);
		G.monoconnect(6, 7);

		INaturalNumber verticesNumber = new NaturalNumber();

		IBooleanVariable[] membership = new IBooleanVariable[G.size()];
		for (int i = 0; i < G.size(); i++)
		{
			membership[i] = BooleanVariable.getBooleanVariable();
		}

		IProblem problem = new Conjunction(new GraphFixer(G), new NaturalNumberFixer(verticesNumber, 5),
				new BitArrayTotaler(membership, verticesNumber), new SubPather(G, membership));

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println(G);
			for (int i = 0; i < G.size(); i++)
			{
				if (membership[i].getValue())
					System.out.println(i);
			}
		}
		else
			System.out.println("No solution.");
	}
}
