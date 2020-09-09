package demos.graphs;

import java.util.ArrayList;

import bits.BitEqualizer;
import bits.BitFixer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import graphs.DirectedGraph;
import graphs.GraphFixer;
import graphs.IDirectedGraph;
import graphs.IGraph;
import graphs.SubGrapher;

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
public class SubGrapherDemo
{
	public static void main(String[] args) throws Exception
	{
		IDirectedGraph G = new DirectedGraph("G", 8);
		G.monoconnect(0, 1);
		G.monoconnect(0, 0);
		G.monoconnect(1, 2);
		G.monoconnect(2, 3);
		G.monoconnect(2, 4);
		G.monoconnect(3, 4);
		G.monoconnect(3, 6);
		G.monoconnect(4, 0);
		G.monoconnect(4, 7);

		IGraph subG = new DirectedGraph("SubG", G.size());
		IBooleanVariable[] membership = new IBooleanVariable[]
		{ BooleanVariable.getBooleanVariable(true), BooleanVariable.getBooleanVariable(true),
				BooleanVariable.getBooleanVariable(false), BooleanVariable.getBooleanVariable(true),
				BooleanVariable.getBooleanVariable(true), BooleanVariable.getBooleanVariable(true),
				BooleanVariable.getBooleanVariable(true), BooleanVariable.getBooleanVariable(true) };

		ArrayList<IProblem> p = new ArrayList<IProblem>();
		for (int i = 0; i < G.size(); i++)
			for (int j = 0; j < G.size(); j++)
			{
				p.add(new Conjunction(
						new Disjunction(new BitFixer(membership[i], false), new BitFixer(membership[j], false),
								new BitEqualizer(subG.getData(i, j), G.getData(i, j))),
						new Disjunction(
								new Conjunction(new BitFixer(membership[i], true), new BitFixer(membership[j], true)),
								new BitFixer(subG.getData(i, j), false))));
			}

		IProblem problem = new Conjunction(new GraphFixer(G), new BitFixer(membership),
				new SubGrapher(G, membership, subG));

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println(G);
			System.out.println(subG);
		}
		else
			System.out.println("No solution.");
	}
}
