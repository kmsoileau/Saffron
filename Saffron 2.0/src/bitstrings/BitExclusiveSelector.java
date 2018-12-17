package bitstrings;

import bits.IBitString;
import bits.IProblem;
import bits.Problem;

/**
 * For any positive integer n, if X_0, X_1, X_2, ... ,X_{n-1} are
 * IBooleanVariables and IBitString bitstring = {X_0, X_1, X_2, ... ,X_{n-1}},
 * then
 * <p>
 * <code>p=new BitExclusiveSelector(bitstring);</code>
 * </p>
 * is satisfied if and only if at <b>EXACTLY</b> one of the following is
 * satisfied:
 * <p>
 * new BitFixer(X_0, true)
 * <p>
 * or
 * <p>
 * new BitFixer(X_1, true)
 * <p>
 * or
 * <p>
 * new BitFixer(X_2, true)
 * <p>
 * or
 * <p>
 * ...
 * <p>
 * new BitFixer(X_{n-1}, true)
 * 
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Dec 17, 2018
 * 
 */
public class BitExclusiveSelector extends Problem implements IProblem
{
	public BitExclusiveSelector(IBitString string) throws Exception
	{
		this.setClauses(new bits.BitExclusiveSelector(string.getBVArray())
				.getClauses());
	}
}
