package naturalnumbers.rectangles;

import bits.Conjunction;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.intervals.NaturalNumberIntervalIntersector;

/**
 * An extension of the Problem class which imposes a relation on two
 * INaturalNumberRectangles. For example, the Problem instance p defined by
 *
 * <p>
 * <code>IProblem p=new NaturalNumberRectangleIntersector(A, B);</code>
 * </p>
 *
 * is satisfied if and only if the rectangles A and B have at least one member
 * in common.
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
public class NaturalNumberRectangleIntersector extends Problem implements IProblem
{
	public NaturalNumberRectangleIntersector(INaturalNumberRectangle rect1, INaturalNumberRectangle rect2)
			throws Exception
	{
		IProblem p = new Conjunction(new NaturalNumberIntervalIntersector(rect1.getBase(), rect2.getBase()),
				new NaturalNumberIntervalIntersector(rect1.getAltitude(), rect2.getAltitude()));

		this.setClauses(p.getClauses());
	}

	public NaturalNumberRectangleIntersector(INaturalNumberRectangle[] rect) throws Exception
	{
		int len = rect.length;
		IProblem[] p = new IProblem[len * (len - 1) / 2];
		int index = 0;
		for (int i = 0; i < len - 1; i++)
		{
			INaturalNumberRectangle rect1 = rect[i];
			for (int j = i + 1; j < len; j++)
			{
				INaturalNumberRectangle rect2 = rect[j];
				p[index++] = new NaturalNumberRectangleIntersector(rect1, rect2);
			}
		}

		this.setClauses((new Conjunction(p)).getClauses());
	}
}