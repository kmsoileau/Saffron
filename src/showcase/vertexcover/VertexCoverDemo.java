package showcase.vertexcover;

import java.util.List;

import naturalnumbers.BitStringTotaler;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import bits.BitFixer;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBitString;
import bits.IBooleanLiteral;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import bitstringgraphs.BitStringGraph;
import bitstringgraphs.BitStringGraphFixer;
import bitstringgraphs.IBitStringGraph;
import bitstrings.BitString;

public class VertexCoverDemo
{
	public static void main(String[] args) throws Exception
	{
		int V = 6;
		IBitString cover = new BitString(V);
		IBitStringGraph graph = new BitStringGraph(V);

		graph.getData(0, 2).setValue(true);
		graph.getData(1, 2).setValue(true);
		graph.getData(2, 0).setValue(true);
		graph.getData(2, 1).setValue(true);
		graph.getData(2, 3).setValue(true);
		graph.getData(3, 2).setValue(true);
		graph.getData(3, 4).setValue(true);
		graph.getData(3, 5).setValue(true);
		graph.getData(4, 3).setValue(true);
		graph.getData(5, 3).setValue(true);

		new BitStringGraphFixer(graph);

		INaturalNumber bitSum = new NaturalNumber(2);
		IProblem problem = new NaturalNumberFixer(bitSum);
		problem = new Conjunction(problem, new BitStringTotaler(cover, bitSum));

		problem = new Conjunction(problem, new BitStringGraphFixer(graph));
		for (int vertex1 = 0; vertex1 < V; vertex1++)
			for (int vertex2 = 0; vertex2 < V; vertex2++)
			{
				IProblem currProblem = new Disjunction(new BitFixer(
						graph.getData(vertex1, vertex2), false), new BitFixer(
						cover.getBooleanVariable(vertex1), true), new BitFixer(
						cover.getBooleanVariable(vertex2), true));
				problem = new Conjunction(problem, currProblem);
			}
		System.out.println(problem);

		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("cover= " + cover.toBits());
		}
		else
			System.out.println("No solution.");
	}
}
