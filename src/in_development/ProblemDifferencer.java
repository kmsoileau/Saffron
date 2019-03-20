package in_development;

import bits.Conjunction;
import bits.IProblem;
import bits.Problem;
import exceptions.bits.ProblemDifferencerException;

/**
 * An extension of the Problem class which expresses the logical difference of
 * two given IProblems. More specifically, the IProblem p defined by
 *
 * <p>
 * <code>IProblem p=new ProblemDifferencer(first,second);</code>
 * </p>
 *
 * is satisfied if and only if the IProblem first is satisfied and the IProblem
 * second is not satisfied.
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2005/10/18
 */
public class ProblemDifferencer extends Problem implements IProblem
{
	public ProblemDifferencer(IProblem first, IProblem second) throws Exception
	{
		if (first == null || second == null)
			throw new ProblemDifferencerException(
					"A null IProblem was passed to constructor.");

		IProblem denial = new ProblemDenier(second);
		IProblem res = new Conjunction(first, denial);
		this.setClauses(res.getClauses());
	}
}