package bits;

import exceptions.bits.ProblemDenierException;

/**
 * An extension of the Problem class which expresses the denial of a given
 * IProblem. More specifically, the IProblem p defined by
 *
 * <p>
 * <code>IProblem p=new ProblemDenier(problem);</code>
 * </p>
 *
 * is satisfied by an ICertificate c if and only if the IProblem problem is not
 * satisfied by c. It should be noted that this does not say anything conclusive
 * about the satisfiability of problem, it is useful mainly in constraining an
 * ICertificate away from those ICertificates which satisfy problem, if any.
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.01
 * @since 2005/12/26
 */
public class ProblemDenier extends Problem implements IProblem
{
	public ProblemDenier(IProblem problem) throws Exception
	{
		if (problem == null)
			throw new ProblemDenierException(
					"Null IProblem passed to constructor.");
		if (problem.numberOfClauses() == 0)
			throw new ProblemDenierException(
					"IProblem of zero size passed to constructor.");

		IProblem res = new ClauseDenier(problem.getClause(0));
		for (int i = 1; i < problem.numberOfClauses(); i++)
		{
			IClause cls = problem.getClause(i);
			IProblem ip = new ClauseDenier(cls);
			res = new Disjunction(res, ip);
		}
		this.setClauses(res.getClauses());
	}
}