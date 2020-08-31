package asdata;

import bits.Conjunction;
import bits.IProblem;
import bits.Problem;

public class ProblemAsDataSolver extends Problem implements IProblem
{
	public ProblemAsDataSolver(IClauseAsData[] clause) throws Exception
	{
		IProblem prob = null;
		for (IClauseAsData cl : clause)
			prob = new Conjunction(prob, new ClauseAsDataSolver(cl));
		this.setClauses(prob.getClauses());
	}

	public ProblemAsDataSolver(IProblemAsData problem) throws Exception
	{
		this(problem.getClausesArray());
	}
}
