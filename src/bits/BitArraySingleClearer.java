package bits;

import bits.exceptions.BitArraySingleClearerException;

/**
 * For any positive integer n, if X_0, X_1, X_2, ... ,X_{n-1} are
 * IBooleanVariables and IBooleanVariable[] array = {X_0, X_1, X_2, ...
 * ,X_{n-1}}, then
 * <p>
 * <code>p=new BitArraySingleClearer(array);</code>
 * </p>
 * is satisfied if and only if at <b>EXACTLY</b> one of the following is
 * satisfied:
 * <p>
 * new BitFixer(X_0, false)
 * <p>
 * or
 * <p>
 * new BitFixer(X_1, false)
 * <p>
 * or
 * <p>
 * new BitFixer(X_2, false)
 * <p>
 * or
 * <p>
 * ...
 * <p>
 * new BitFixer(X_{n-1}, false)
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 9, 2005
 */
public class BitArraySingleClearer extends Problem implements IProblem
{
	public BitArraySingleClearer(IBooleanVariable[] array) throws Exception
	{
		if (array == null)
			throw new BitArraySingleClearerException("Null array passed to constructor");
		int bits = array.length;
		if (bits == 0)
			throw new BitArraySingleClearerException("Array of length zero passed to constructor");

		IProblem problem = Problem.newProblem();
		IClause clause = Clause.newClause();
		for (int i = 0; i < bits; i++)
			clause = clause.or(array[i]);
		problem.addClause(clause);

		for (int i = 0; i < bits; i++)
			for (int j = i + 1; j < bits; j++)
				problem.addClause(Clause.newClause().or(array[i]).or(array[j]));

		this.setClauses(problem.getClauses());
	}
}
