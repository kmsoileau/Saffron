package showcase.vertexcover;

import bits.BitFixer;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBitString;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;
import graphs.DirectedGraph;
import graphs.GraphFixer;
import graphs.IDirectedGraph;
import graphs.IGraph;
import naturalnumbers.BitStringTotaler;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;

public class MinimumVertexCoverDemo
{
	public static void main(String[] args) throws Exception
	{
		int V = 6; // The number of vertices

		IDirectedGraph G = new DirectedGraph(V);
		G.biconnect(0, 2);
		G.biconnect(1, 2);
		G.biconnect(2, 3);
		G.biconnect(3, 4);
		G.biconnect(3, 5);

		IBitString cover = minimumVertexCover(G);

		System.out.print("GRAPH\n" + G);
		System.out.print("\n\nSOLUTION\nMinimum Vertex Cover = {");
		for (int i = 0; i < cover.size(); i++)
			if (cover.getBooleanVariable(i).getValue())
				System.out.print(" " + i);
		System.out.print(" }");
	}

	public static IBitString minimumVertexCover(IGraph G) throws Exception
	{
		int V = G.getData().length;

		IBitString cover = new BitString(V);

		for (int K = 1; K <= V; K++)
		{
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

			IProblemMessage s = problem0.findModel(Problem.defaultSolver());
			if (s.getStatus() == IProblemMessage.SATISFIABLE)
			{
				BooleanLiteral.interpret(s.getLiterals());
				return cover;
			}
			else
				continue;
		}
		return null;
	}
}
