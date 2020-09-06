package asdata.inwork;

import asdata.IProblemAsData;
import asdata.ProblemAsDataNonsolver;
import asdata.ProblemAsDataSolver;
import bits.Conjunction;
import bits.IProblem;
import bits.Problem;

//Incomplete
public class LogicalDifference extends Problem implements IProblem
{
	/*
	 * Satisfied by a model that satisfies p1 but not p2
	 */
	public LogicalDifference(IProblemAsData p1, IProblemAsData p2)
			throws Exception
	{
		IProblem prob = null;
		prob = new Conjunction(new ProblemAsDataSolver(p1),
				new ProblemAsDataNonsolver(p2));
		this.setClauses(prob.getClauses());
	}
}
