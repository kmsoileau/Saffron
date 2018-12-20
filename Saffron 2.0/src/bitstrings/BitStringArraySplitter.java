package bitstrings;

import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import exceptions.bitstrings.BitStringArraySplitterException;

/**
 *
 * Two constraints are imposed by this <code>IProblem</code>:
 * <p>
 * 1. <code>S1</code> and <code>S2</code> are bitwise negations of each other;
 * <p>
 * 2. For each <code>i</code>, <code>X[i]</code> is not dominated* by
 * <code>S1</code>;
 * <p>
 * 3. For each <code>i</code>, <code>X[i]</code> is not dominated by
 * <code>S2</code>;
 * <p>
 * An equivalent statement is that <code>S1</code> and <code>S2</code> split
 * each <code>X[i]</code> into nonzero <code>IBitString</code>s whose bitwise
 * AND is the zero <code>IBitString</code>.
 * <p>
 * *We say that <code>IBitString X</code> is dominated by
 * <code>IBitString Y</code> if and only if the bitwise AND of <code>X</code>
 * and <code>Y</code> equals <code>X</code>.
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
