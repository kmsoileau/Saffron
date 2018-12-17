package bitstrings;

import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import exceptions.bitstrings.BitStringArraySplitterException;

/**
 *
 * Two constraints are imposed by this IProblem:
 * <p>
 * 1. S1 and S2 are bitwise negations of each other;
 * <p>
 * 2. For each i, X[i] is not dominated* by S1;
 * <p>
 * 3. For each i, X[i] is not dominated by S2;
 * <p>
 * An equivalent statement is that S1 and S2 split each X[i] into nonzero,
 * non-overlapping IBitStrings.
 * 
 * <p>
 * *We say that IBitString X is dominated by IBitString Y if and only if the
 * bitwise AND of X and Y equals X.
 * 
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2018/12/11
 */
public class BitStringArraySplitter extends Problem implements IProblem
{
	public BitStringArraySplitter(IBitString[] X, IBitString S1, IBitString S2)
			throws Exception
	{
		if (X == null)
			throw new BitStringArraySplitterException(
					"Null passed to constructor as IBitString array.");
		if (S1 == null || S2 == null)
			throw new BitStringArraySplitterException(
					"Null passed to constructor as IBitString.");
		if (X.length == 0)
			throw new BitStringArraySplitterException(
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
