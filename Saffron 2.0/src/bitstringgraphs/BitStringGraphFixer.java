package bitstringgraphs;

import bits.BitFixer;
import bits.Conjunction;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;

public class BitStringGraphFixer extends Problem
{
	public BitStringGraphFixer(IBitStringGraph graph) throws Exception
	{
		IBooleanVariable[][] work = graph.getData();
		IProblem problem = Problem.newProblem();
		int len = work.length;
		for (int i = 0; i < len; i++)
		{
			IBooleanVariable[] wi = work[i];
			for (int j = 0; j < len; j++)
				problem = new Conjunction(problem, new BitFixer(wi[j]));
		}
		this.setClauses(problem.getClauses());
	}
}
