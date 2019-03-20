package bits;

import in_development.ProblemBitLinker;
import exceptions.bits.ProblemIndexedConjunctionException;

/**
 * The <code>IProblem prob</code> defined by
 * <p>
 * <code>IProblem prob=new ProblemIndexedConjunction(P, Q);</code>
 * <p>
 * for <code>IProblem[] P</code> and <code>IProblem[] Q,</code> is satisfied if
 * and only if for all of the <code>P[i]</code> that are satisfied, all of the
 * corresponding <code>Q[i]</code> are satisfied.
 * <p>
 * For example, suppose <code>P[2], P[3] and P[6]</code> are satisfied and
 * <code>P[0], P[1], P[4], P[5]
 * and P[7]</code> are not. Then
 * <p>
 * <code>IProblem prob=new ProblemIndexedConjunction(new IProblem[]{P[0], P[1], P[2],
 * P[3], P[4], P[5], P[6], P[7]}, new IProblem[]{Q[0], Q[1], Q[2], Q[3], Q[4], Q[5],
 * Q[6], Q[7]});</code>
 * <p>
 * is equivalent to <code>new Conjunction(Q[2], Q[3], Q[6])</code>.
 * 
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Dec 25, 2018
 */
public class ProblemIndexedConjunction extends Problem implements IProblem
{
	private IBooleanVariable[] bitLink;

	public ProblemIndexedConjunction(IProblem[] P, IProblem[] Q)
			throws Exception
	{
		if (P == null || Q == null)
			throw new ProblemIndexedConjunctionException(
					"Null passed to constructor as IProblem.");

		if (P.length != Q.length)
			throw new ProblemIndexedConjunctionException(
					"IProblem arrays of unequal length passed to constructor.");
		if (P.length == 0 || Q.length == 0)
		{
			this.setClauses(Problem.trivialProblem().getClauses());
		}
		else
		{
			int len = P.length;
			IProblem[] pf = new IProblem[len];
			IProblem[] pbl = new IProblem[len];
			this.bitLink = new IBooleanVariable[len];
			for (int i = 0; i < len; i++)
			{
				bitLink[i] = BooleanVariable.getBooleanVariable();
				pbl[i] = new ProblemBitLinker(P[i], bitLink[i]);
				pf[i] = new ProblemFork(bitLink[i], Q[i],
						Problem.trivialProblem());
			}
			IProblem problem = new Conjunction(new Conjunction(pbl),
					new Conjunction(pf));
			this.setClauses(problem.getClauses());
		}
	}

	public IBooleanVariable[] getBitLinkArray()
	{
		return bitLink;
	}
}
