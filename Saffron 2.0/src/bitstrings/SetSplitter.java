package bitstrings;

import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import exceptions.bitstrings.SetSplitterException;

/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2018/12/11
 */
public class SetSplitter extends Problem implements IProblem
{
	public SetSplitter(IBitString[] X, IBitString S1, IBitString S2)
			throws Exception
	{
		if (X == null)
			throw new SetSplitterException(
					"Null passed to constructor as IBitString array.");
		if (S1 == null || S2 == null)
			throw new SetSplitterException(
					"Null passed to constructor as IBitString.");
		if (X.length == 0)
			throw new SetSplitterException(
					"IBitString array of zero length passed to constructor.");

		IProblem problem = Problem.newProblem();

		// Bind S1 to the bitwise negation of S2
		problem = new Conjunction(problem, new BitStringNoter(S1, S2));

		// Constrain each X[i] to not be dominated by either S1 or S2
		for (int i = 0; i < X.length; i++)
			problem = new Conjunction(problem, new BitStringNonDominator(X[i],
					S1), new BitStringNonDominator(X[i], S2));

		this.setClauses(problem.getClauses());
	}
}
