package naturalnumbers.intervals;

import bits.Conjunction;
import bits.Disjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.NaturalNumberOrderer;
import naturalnumbers.NaturalNumberStrictOrderer;
import naturalnumbers.intervals.exceptions.NaturalNumberIntervalDisjointerException;

/**
 * An extension of the Problem class which imposes a relation on two
 * INaturalNumberIntervals. For example, the Problem instance p defined by
 *
 * <p>
 * <tt>IProblem p=new NaturalNumberIntervalDisjointer(A, B);</tt>
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
public class NaturalNumberIntervalDisjointer extends Problem implements IProblem
{
	public NaturalNumberIntervalDisjointer(INaturalNumber A, INaturalNumber B, INaturalNumber C, INaturalNumber D)
			throws Exception
	{
		if (A == null || B == null || C == null || D == null)
			throw new NaturalNumberIntervalDisjointerException("Null INaturalNumber passed to constructor.");

		this.setClauses(new Conjunction(new NaturalNumberOrderer(A, B), new NaturalNumberOrderer(C, D),
				new Disjunction(new NaturalNumberStrictOrderer(B, C), new NaturalNumberStrictOrderer(D, A)))
						.getClauses());
	}

	public NaturalNumberIntervalDisjointer(INaturalNumberInterval theOne, INaturalNumberInterval theOther)
			throws Exception
	{
		this(theOne.getLower(), theOne.getUpper(), theOther.getLower(), theOther.getUpper());
	}
}

// public NaturalNumberIntervalDisjointer(INaturalNumberInterval theOne,
// INaturalNumberInterval theOther) throws Exception
// {
// IProblem pX0X1 = new NaturalNumberOrderer(theOne.getLower(),
// theOne.getUpper());
// IProblem pY0Y1 = new NaturalNumberOrderer(theOther.getLower(),
// theOther.getUpper());
// IProblem qX1Y0 = new NaturalNumberStrictOrderer(theOne.getUpper(),
// theOther.getLower());
// IProblem qY1X0 = new NaturalNumberStrictOrderer(theOther.getUpper(),
// theOne.getLower());
//
// IProblem p = new Conjunction(new Conjunction(pX0X1, pY0Y1),
// new Disjunction(qX1Y0, qY1X0));
//
// this.setClauses(p.getClauses());
// }