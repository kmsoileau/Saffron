package naturalnumbers.rectangles;

import bits.Conjunction;
import bits.Disjunction;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.intervals.NaturalNumberIntervalDisjointer;
import naturalnumbers.rectangles.exceptions.NaturalNumberRectangleDisjointerException;

/**
 * An extension of the Problem class which imposes a relation on two
 * INaturalNumberRectangles. For example, the Problem instance p defined by
 *
 * <p>
 * <code>IProblem p=new NaturalNumberRectangleDisjointer(A, B);</code>
 * </p>
 *
 * is satisfied if and only if the rectangles A and B have no members in common.
 * <p>
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2019/02/02
 */
public class NaturalNumberRectangleDisjointer extends Problem implements IProblem
{
	public NaturalNumberRectangleDisjointer(INaturalNumberRectangle rect1, INaturalNumberRectangle rect2)
			throws Exception
	{
		if (rect1 == null || rect2 == null)
			throw new NaturalNumberRectangleDisjointerException(
					"Null INaturalNumberRectangle was passed to constructor.");
		this.setClauses(new Disjunction(new NaturalNumberIntervalDisjointer(rect1.getBase(), rect2.getBase()),
				new NaturalNumberIntervalDisjointer(rect1.getAltitude(), rect2.getAltitude())).getClauses());
	}

	public NaturalNumberRectangleDisjointer(INaturalNumberRectangle[] rect) throws Exception
	{
		if (rect == null)
			throw new NaturalNumberRectangleDisjointerException(
					"Null INaturalNumberRectangle array was passed to constructor.");

		int len = rect.length;
		IProblem[] p = new IProblem[len * (len - 1) / 2];
		int index = 0;
		for (int i = 0; i < len - 1; i++)
		{
			INaturalNumberRectangle rect1 = rect[i];
			for (int j = i + 1; j < len; j++)
			{
				INaturalNumberRectangle rect2 = rect[j];
				p[index++] = new NaturalNumberRectangleDisjointer(rect1, rect2);
			}
		}

		this.setClauses((new Conjunction(p)).getClauses());
	}
}