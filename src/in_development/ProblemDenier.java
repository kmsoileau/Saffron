package in_development;

import bits.ClauseDenier;
import bits.Disjunction;
import bits.IProblem;
import bits.Problem;
import exceptions.bits.ProblemDenierException;

public class ProblemDenier extends Problem implements IProblem
{
	public ProblemDenier(IProblem problem) throws Exception
	{
		if (problem == null)
			throw new ProblemDenierException(
					"Null IProblem passed to constructor.");
		if (problem.size() == 0)
			throw new ProblemDenierException(
					"Empty IProblem passed to constructor.");

		int size = problem.size();
		if (size == 0)

		{
			this.setClauses(Problem.trivialProblem().getClauses());
		} else
		{
			IProblem[] r = new IProblem[size];
			for (int i = 0; i < size; i++)
			{
				r[i] = new ClauseDenier(problem.getClause(i));
			}
			this.setClauses(new Disjunction(r).getClauses());
		}
	}
}
