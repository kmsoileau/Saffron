/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 4, 2019
 */
package integers.intervals;

import bits.Conjunction;
import bits.IProblem;
import bits.Problem;
import integers.IInteger;
import integers.Integer;
import integers.IntegerAdder;

/**
 * 
 *
 */
public class IntegerIntervalCongruenter extends Problem implements IProblem
{
	public IntegerIntervalCongruenter(IIntegerInterval A, IIntegerInterval B) throws Exception
	{
		this(A, B, new Integer());
	}

	public IntegerIntervalCongruenter(IIntegerInterval A, IIntegerInterval B, IInteger X) throws Exception
	{
		IProblem p1 = new Conjunction(new IntegerAdder(A.getLower(), X, B.getLower()),
				new IntegerAdder(A.getUpper(), X, B.getUpper()));

		this.setClauses(p1.getClauses());
	}
}
