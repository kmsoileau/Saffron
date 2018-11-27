package naturalnumbers;

import bits.Conjunction;
import bits.Disjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

/**
 * An extension of the Problem class which imposes a Boolean relation on four
 * INaturalNumbers. For example, the Problem instance p defined by
 *
 * <p>
 * <tt>IProblem p=new NaturalNumberIntervalIntersector(X0, X1, Y0, Y1);
 * </p>
 *
 * is satisfied if and only if
 * <p>
 * X0<=X1,
 * <p>
 * Y0<=Y1, and
 * <p>
 * the sets of natural numbers {X0.toDecimal(), X0.toDecimal()+1, ... ,
 * X1.toDecimal()} and
 * <p>
 * {Y0.toDecimal(), Y0.toDecimal()+1, ... , Y1.toDecimal()} have members in
 * common.
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
	public NaturalNumberIntervalIntersector(INaturalNumber X0,
			INaturalNumber X1, INaturalNumber Y0, INaturalNumber Y1)
			throws Exception
	{
		IProblem pX0X1 = new NaturalNumberOrderer(X0, X1);
		IProblem pY0X0 = new NaturalNumberOrderer(Y0, X0);
		IProblem pX0Y1 = new NaturalNumberOrderer(X0, Y1);
		IProblem pY0X1 = new NaturalNumberOrderer(Y0, X1);
		IProblem pX1Y1 = new NaturalNumberOrderer(X1, Y1);
		IProblem pY0Y1 = new NaturalNumberOrderer(Y0, Y1);
		IProblem pX0Y0 = new NaturalNumberOrderer(X0, Y0);
		IProblem pY1X1 = new NaturalNumberOrderer(Y1, X1);

		IProblem p = new Disjunction(new Conjunction(pX0X1, pY0X0, pX0Y1),
				new Conjunction(pX0X1, pY0X1, pX1Y1), new Conjunction(pY0Y1,
						pX0Y0, pY0X1), new Conjunction(pY0Y1, pX0Y1, pY1X1));
		this.setClauses(p.getClauses());
	}
}