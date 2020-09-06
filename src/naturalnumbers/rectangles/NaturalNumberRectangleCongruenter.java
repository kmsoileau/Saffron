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
package naturalnumbers.rectangles;

import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.NaturalNumber;
import naturalnumbers.intervals.NaturalNumberIntervalCongruenter;
import naturalnumbers.intervals.NaturalNumberIntervalEqualizer;

/**
 * 
 *
 */
public class NaturalNumberRectangleCongruenter extends Problem implements
		IProblem
{
	public NaturalNumberRectangleCongruenter(INaturalNumberRectangle A,
			INaturalNumberRectangle B) throws Exception
	{
		this(A, B, new NaturalNumber(), new NaturalNumber());
	}

	public NaturalNumberRectangleCongruenter(INaturalNumberRectangle A,
			INaturalNumberRectangle B, INaturalNumber X, INaturalNumber Y)
			throws Exception
	{
		IProblem p = new Conjunction(new NaturalNumberIntervalCongruenter(
				A.getBase(), B.getBase(), X),
				new NaturalNumberIntervalCongruenter(A.getAltitude(), B
						.getAltitude(), Y));
		this.setClauses(p.getClauses());
	}

	public NaturalNumberRectangleCongruenter(INaturalNumberRectangle A,
			INaturalNumberRectangle B, int sense, INaturalNumber D)
			throws Exception
	{
		IProblem p = null;

		if (sense == INaturalNumberRectangle.HORIZONTAL)
		{
			p = new Conjunction(new NaturalNumberIntervalCongruenter(
					A.getBase(), B.getBase(), D),
					new NaturalNumberIntervalEqualizer(A.getAltitude(), B
							.getAltitude()));
		}
		else if (sense == INaturalNumberRectangle.VERTICAL)
		{
			p = new Conjunction(new NaturalNumberIntervalEqualizer(A.getBase(),
					B.getBase()), new NaturalNumberIntervalCongruenter(
					A.getAltitude(), B.getAltitude(), D));
			this.setClauses(p.getClauses());
		}

		this.setClauses(p.getClauses());
	}
}
