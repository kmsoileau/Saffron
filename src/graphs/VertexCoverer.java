package graphs;

import bits.BitFixer;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBitString;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.BitStringTotaler;
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
 * @since Jan 18, 2019
 */
public class VertexCoverer extends Problem implements IProblem
{
	public VertexCoverer(GraphVertexSubset Q, int K) throws Exception
	{
		int V = Q.getBase().size();
		IBitString cover = Q.getSubsetMembership().getMembership();
		INaturalNumber nnVerticesInCover = new NaturalNumber(K);

		IProblem problem0 = new Conjunction(new NaturalNumberFixer(nnVerticesInCover),
				new BitStringTotaler(cover, nnVerticesInCover), new GraphFixer(Q.getBase()));
		for (int vertex1 = 0; vertex1 < V; vertex1++)
			for (int vertex2 = 0; vertex2 < V; vertex2++)
			{
				IProblem currProblem = new Disjunction(new BitFixer(Q.getBase().getData(vertex1, vertex2), false),
						new BitFixer(cover.getBooleanVariable(vertex1), true),
						new BitFixer(cover.getBooleanVariable(vertex2), true));
				problem0 = new Conjunction(problem0, currProblem);
			}

		this.setClauses(problem0.getClauses());
	}

	public VertexCoverer(IGraph G, int K, IBitString cover) throws Exception
	{
		int V = G.getData().length;
		INaturalNumber nnVerticesInCover = new NaturalNumber(K);
		IProblem nnfb = new NaturalNumberFixer(nnVerticesInCover);
		IProblem bst = new BitStringTotaler(cover, nnVerticesInCover);
		IProblem bsgf = new GraphFixer(G);

		IProblem problem0 = new Conjunction(nnfb, bst, bsgf);
		for (int vertex1 = 0; vertex1 < V; vertex1++)
			for (int vertex2 = 0; vertex2 < V; vertex2++)
			{
				IProblem currProblem = new Disjunction(new BitFixer(G.getData(vertex1, vertex2), false),
						new BitFixer(cover.getBooleanVariable(vertex1), true),
						new BitFixer(cover.getBooleanVariable(vertex2), true));
				problem0 = new Conjunction(problem0, currProblem);
			}

		this.setClauses(problem0.getClauses());
	}
}
