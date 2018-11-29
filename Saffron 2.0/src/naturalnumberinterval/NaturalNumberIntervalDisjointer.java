package naturalnumberinterval;

import naturalnumbers.NaturalNumberOrderer;
import naturalnumbers.NaturalNumberStrictOrderer;
import bits.Conjunction;
import bits.Disjunction;
import bits.IProblem;
import bits.Problem;

/**
 * An extension of the Problem class which imposes a relation on two
 * INaturalNumberIntervals. For example, the Problem instance p defined by
 *
 * <p>
 * <tt>IProblem p=new NaturalNumberIntervalDisjointer(A, B);
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
public class NaturalNumberIntervalDisjointer extends Problem implements
		IProblem
{
	public NaturalNumberIntervalDisjointer(INaturalNumberInterval theOne,
			INaturalNumberInterval theOther) throws Exception
	{
		IProblem pX0X1 = new NaturalNumberOrderer(theOne.getLower(),
				theOne.getUpper());
		IProblem pY0Y1 = new NaturalNumberOrderer(theOther.getLower(),
				theOther.getUpper());
		IProblem qX1Y0 = new NaturalNumberStrictOrderer(theOne.getUpper(),
				theOther.getLower());
		IProblem qY1X0 = new NaturalNumberStrictOrderer(theOther.getUpper(),
				theOne.getLower());

		IProblem p = new Conjunction(new Conjunction(pX0X1, pY0Y1),
				new Disjunction(qX1Y0, qY1X0));

		this.setClauses(p.getClauses());
	}
}