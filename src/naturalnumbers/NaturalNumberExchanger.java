package naturalnumbers;

import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 3, 2009
 */
public class NaturalNumberExchanger extends Problem implements IProblem
{
	public NaturalNumberExchanger(INaturalNumber xBefore,
			INaturalNumber yBefore, INaturalNumber xAfter, INaturalNumber yAfter)
			throws Exception
	{
		IProblem p = new Conjunction(
				new NaturalNumberEqualizer(xBefore, yAfter),
				new NaturalNumberEqualizer(yBefore, xAfter));

		this.setClauses(p.getClauses());
	}
}