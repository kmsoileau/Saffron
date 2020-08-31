package asdata;

import bits.Disjunction;
import bits.IProblem;
import bits.Problem;

/*
 * This problem is satisfied by any model that is not a solution of IProblemAsData problem
 */
public class ProblemAsDataNonsolver extends Problem implements IProblem
{

	public ProblemAsDataNonsolver(IClauseAsData[] clause) throws Exception
	{
		IProblem prob = null;
		for (IClauseAsData cl : clause)
			prob = new Disjunction(prob, new ClauseAsDataNonsolver(cl));
		this.setClauses(prob.getClauses());
	}

	public ProblemAsDataNonsolver(IProblemAsData problem) throws Exception
	{
		this(problem.getClausesArray());
	}
}
