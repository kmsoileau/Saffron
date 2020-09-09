/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 6, 2019
 */
package graphs;

import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.BitStringEqualizer;
import bits.strings.BitStringFixer;
import bits.strings.BitStringOrer;
import bits.strings.BitStringSetter;
import bits.strings.BitStringSubtractor;

/**
 * 
 *
 */
public class Connector extends Problem implements IProblem
{
	public Connector(IUndirectedGraph graph) throws Exception
	{
		int numberVertices = graph.size();
		int start = 0;
		IBitString[] G = new IBitString[graph.size() + 1];
		IBitString[] B = new IBitString[graph.size() + 1];
		IBitString[] C = new IBitString[graph.size() + 1];
		IProblem[] GInit = new IProblem[graph.size()];
		IProblem[] BInit = new IProblem[graph.size()];
		IProblem[] CInit = new IProblem[graph.size()];

		B[0] = new BitString(numberVertices);
		G[0] = new BitString(numberVertices);
		C[0] = new BitString(numberVertices);
		GInit[0] = new BitStringFixer(G[0], new int[]
		{ start });
		BInit[0] = new BitStringEqualizer(B[0], G[0]);
		CInit[0] = new Neighborer(graph, B[0], C[0]);

		for (int i = 0; i < graph.size() - 1; i++)
		{
			B[i + 1] = new BitString(numberVertices);
			G[i + 1] = new BitString(numberVertices);
			C[i + 1] = new BitString(numberVertices);
			GInit[i + 1] = new BitStringOrer(G[i], B[i + 1], G[i + 1]);
			BInit[i + 1] = new BitStringSubtractor(C[i], G[i], B[i + 1]);
			CInit[i + 1] = new Neighborer(graph, B[i + 1], C[i + 1]);
		}

		IBitString allOnes = new BitString(graph.size());

		IProblem problem = new Conjunction(new IProblem[]
		{ new GraphFixer(graph), new Conjunction(GInit), new Conjunction(BInit), new Conjunction(CInit),
				new BitStringSetter(allOnes), new BitStringEqualizer(G[graph.size() - 1], allOnes) });

		this.setClauses(problem.getClauses());
	}
}