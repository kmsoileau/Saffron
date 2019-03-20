/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Dec 24, 2018
 */
package bits;

import exceptions.bits.ProblemForkException;

/**
 * An extension of the Problem class which Is equivalent to
 * <code>IProblem</code>s <code>P</code> or <code>Q</code> according to whether
 * the <code>IBooleanVariable b</code> is <code>true</code> or
 * <code>false</code>, respectively. More specifically, the
 * <code>IProblem p</code> defined by
 *
 * <p>
 * <code>IProblem p=new ProblemFork(b, P, Q)</code>
 * </p>
 *
 * is satisfied if and only if
 * <p>
 * 1. <code>b</code> is <code>true</code> and <code>P</code> is satisfied, or
 * <p>
 * 2. <code>b</code> is <code>false</code> and <code>Q</code> is satisfied.
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2018/12/24
 */
public class ProblemFork extends Problem implements IProblem
{
	public ProblemFork(IBooleanVariable b, IProblem P, IProblem Q)
			throws Exception
	{
		if (b == null)
			throw new ProblemForkException(
					"Null IBooleanVariable passed to constructor.");
		if (P == null || Q == null)
			throw new ProblemForkException(
					"Null IProblem passed to constructor.");
		// (!b | P) & (b | Q)
		IProblem p1 = new Disjunction(new BitFixer(b, false), P);
		IProblem p2 = new Disjunction(new BitFixer(b, true), Q);
		this.setClauses(new Conjunction(p1, p2).getClauses());
	}
}
