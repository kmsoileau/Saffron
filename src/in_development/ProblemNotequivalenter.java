package in_development;

import bits.Conjunction;
import bits.Disjunction;
import bits.IProblem;
import bits.Problem;
import exceptions.bits.ProblemNotequivalenterException;

/**
 * An extension of the Problem class which expresses the logical nonequivalence
 * of two given IProblems. More specifically, the IProblem p defined by
 *
 * <p>
 * <code>IProblem p=new ProblemNotequivalenter(first,second);</code>
 * </p>
 *
 * is satisfied if and only if some certificate for IProblem first is a not a
 * certificate for IProblem second, or vice versa.
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2018/12/29
 */
public class ProblemNotequivalenter extends Problem implements IProblem
{
	public ProblemNotequivalenter(IProblem first, IProblem second)
			throws Exception
	{
		if (first == null || second == null)
			throw new ProblemNotequivalenterException(
					"A null IProblem was passed to constructor.");

		this.setClauses(new Disjunction(new Conjunction(first,
				new ProblemDenier(second)), new Conjunction(second,
				new ProblemDenier(first))).getClauses());
	}
}