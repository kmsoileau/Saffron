package integers.intervals;

import bits.Conjunction;
import bits.Disjunction;
import bits.IProblem;
import bits.Problem;
import integers.IInteger;
import integers.IntegerOrderer;
import integers.IntegerStrictOrderer;
import integers.intervals.exceptions.IntegerIntervalDisjointerException;

/**
 * An extension of the Problem class which imposes a relation on two
 * IIntegerIntervals. For example, the Problem instance p defined by
 *
 * <p>
 * <tt>IProblem p=new IntegerIntervalDisjointer(A, B);</tt>
 * </p>
 *
 * is satisfied if and only if the intervals A and B have no members in common.
 * <p>
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2018/11/27
 */
public class IntegerIntervalDisjointer extends Problem implements IProblem
{
	public IntegerIntervalDisjointer(IInteger A, IInteger B, IInteger C, IInteger D) throws Exception
	{
		if (A == null || B == null || C == null || D == null)
			throw new IntegerIntervalDisjointerException("Null IInteger passed to constructor.");

		this.setClauses(new Conjunction(new IntegerOrderer(A, B), new IntegerOrderer(C, D),
				new Disjunction(new IntegerStrictOrderer(B, C), new IntegerStrictOrderer(D, A))).getClauses());
	}

	public IntegerIntervalDisjointer(IIntegerInterval theOne, IIntegerInterval theOther) throws Exception
	{
		this(theOne.getLower(), theOne.getUpper(), theOther.getLower(), theOther.getUpper());
	}
}