package bits.strings;

import java.util.ArrayList;

import bits.Clause;
import bits.IBitString;
import bits.IClause;
import bits.IProblem;
import bits.Problem;
import bits.strings.exceptions.BitStringSingleClearerException;

/**
 * For any positive integer n, if X_0, X_1, X_2, ... ,X_{n-1} are
 * IBooleanVariables and IBooleanVariable[] array = {X_0, X_1, X_2, ...
 * ,X_{n-1}}, then
 * <p>
 * <code>p=new BitStringSingleClearer(array);</code>
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
public class BitStringSingleClearer extends Problem implements IProblem
{
	public BitStringSingleClearer(IBitString bs) throws Exception
	{
		if (bs == null)
			throw new BitStringSingleClearerException("Null array passed to constructor");
		int bits = bs.size();
		if (bits == 0)
			throw new BitStringSingleClearerException("Array of length zero passed to constructor");

		ArrayList<IClause> ret = new ArrayList<IClause>();
		IClause clause = Clause.newClause();
		for (int i = 0; i < bits; i++)
			clause = clause.or(bs.getBooleanVariable(i));
		ret.add(clause);

		for (int i = 0; i < bits; i++)
			for (int j = i + 1; j < bits; j++)
				ret.add(Clause.newClause().or(bs.getBooleanVariable(i)).or(bs.getBooleanVariable(j)));

		this.setClauses(ret);
	}
}
