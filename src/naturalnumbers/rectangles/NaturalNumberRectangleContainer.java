/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 5, 2019
 */
package naturalnumbers.rectangles;

import bits.Conjunction;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.intervals.NaturalNumberIntervalContainer;

/**
 * 
 *
 */
public class NaturalNumberRectangleContainer extends Problem implements IProblem
{
	public NaturalNumberRectangleContainer(INaturalNumberRectangle L, INaturalNumberRectangle R) throws Exception
	{
		IProblem p = new Conjunction(new NaturalNumberIntervalContainer(L.getBase(), R.getBase()),
				new NaturalNumberIntervalContainer(L.getAltitude(), R.getAltitude()));

		this.setClauses(p.getClauses());
	}
}
