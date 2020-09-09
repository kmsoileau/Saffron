package asdata;

import bits.Conjunction;
import bits.Disjunction;
import bits.IProblem;
import bits.Problem;

public class ProblemAsDataNonequivalenter extends Problem implements IProblem
{
	public ProblemAsDataNonequivalenter(IProblemAsData p1, IProblemAsData p2) throws Exception
	{
		/*
		 * There exists a solution to p1 that doesn't solve p2, or there exists a
		 * solution to p2 that doesn't solve p1
		 */
		IProblem prob = new Disjunction(new Conjunction(new ProblemAsDataSolver(p1), new ProblemAsDataNonsolver(p2)),
				new Conjunction(new ProblemAsDataNonsolver(p1), new ProblemAsDataSolver(p2)));
		this.setClauses(prob.getClauses());
	}
}
