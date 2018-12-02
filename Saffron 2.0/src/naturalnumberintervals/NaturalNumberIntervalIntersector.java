package naturalnumberintervals;

import naturalnumbers.NaturalNumberOrderer;
import bits.Conjunction;
import bits.Disjunction;
import bits.IProblem;
import bits.Problem;

/**
 * An extension of the Problem class which imposes a relation on two
 * INaturalNumberIntervals. For example, the Problem instance p defined by
 *
 * <p>
 * <tt>IProblem p=new NaturalNumberIntervalIntersector(A, B);
 * </p>
 *
 * is satisfied if and only if the intervals A and B have at least one member in
 * common.
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
public class NaturalNumberIntervalIntersector extends Problem implements
		IProblem
{
	public NaturalNumberIntervalIntersector(INaturalNumberInterval theOne,
			INaturalNumberInterval theOther) throws Exception
	{
		IProblem pX0X1 = new NaturalNumberOrderer(theOne.getLower(),
				theOne.getUpper());
		IProblem pY0X0 = new NaturalNumberOrderer(theOther.getLower(),
				theOne.getLower());
		IProblem pX0Y1 = new NaturalNumberOrderer(theOne.getLower(),
				theOther.getUpper());
		IProblem pY0X1 = new NaturalNumberOrderer(theOther.getLower(),
				theOne.getUpper());
		IProblem pX1Y1 = new NaturalNumberOrderer(theOne.getUpper(),
				theOther.getUpper());
		IProblem pY0Y1 = new NaturalNumberOrderer(theOther.getLower(),
				theOther.getUpper());
		IProblem pX0Y0 = new NaturalNumberOrderer(theOne.getLower(),
				theOther.getLower());
		IProblem pY1X1 = new NaturalNumberOrderer(theOther.getUpper(),
				theOne.getUpper());

		IProblem p = new Disjunction(new Conjunction(pX0X1, pY0X0, pX0Y1),
				new Conjunction(pX0X1, pY0X1, pX1Y1), new Conjunction(pY0Y1,
						pX0Y0, pY0X1), new Conjunction(pY0Y1, pX0Y1, pY1X1));
		this.setClauses(p.getClauses());
	}
}