package bits;

import bits.exceptions.ProblemInnerProductException;

/**
 * The <code>IProblem prob</code> defined by
 * <p>
 * <code>IProblem prob=new ProblemInnerProduct(P, Q);</code>
 * <p>
 * for <code>IProblem[] P</code> and <code>IProblem[] Q,</code> is satisfied if
 * and only if for all of the <code>P[i]</code> that are satisfied, at least one
 * of the corresponding <code>Q[i]</code> are satisfied.
 * <p>
 * For example, suppose <code>P[2], P[3] and P[6]</code> are satisfied and
 * <code>P[0], P[1], P[4], P[5] and P[7]</code> are not. Then
 * <p>
 * <code>IProblem prob=new ProblemInnerProduct(new IProblem[]{P[0], P[1], P[2],
 * P[3], P[4], P[5], P[6], P[7]}, new IProblem[]{Q[0], Q[1], Q[2], Q[3], Q[4], Q[5],
 * Q[6], Q[7]});</code>
 * <p>
 * is equivalent to <code>new Disjunction(Q[2], Q[3], Q[6])</code>.
 * <p>
 * One may regard this IProblem as a problem-indexed Disjunction.
 * 
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Dec 24, 2018
 */
public class ProblemInnerProduct extends Problem implements IProblem
{
	public ProblemInnerProduct(IProblem[] P, IProblem[] Q) throws Exception
	{
		if (P == null || Q == null)
			throw new ProblemInnerProductException("Null array passed to constructor.");
		IProblem[] r = new IProblem[P.length];
		for (int i = 0; i < P.length; i++)
		{
			r[i] = new Conjunction(P[i], Q[i]);
		}
		this.setClauses(new Disjunction(r).getClauses());
	}

	public ProblemInnerProduct(ProblemVector P, ProblemVector Q) throws Exception
	{
		this(P.getBacking(), Q.getBacking());
	}
}
