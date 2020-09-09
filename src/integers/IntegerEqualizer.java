/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 10, 2019
 */
package integers;

import bits.BitEqualizer;
import bits.BitFixer;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import integers.exceptions.IntegerEqualizerException;
import naturalnumbers.NaturalNumberEqualizer;

/**
 * 
 *
 */
public class IntegerEqualizer extends Problem implements IProblem
{
	public IntegerEqualizer(IInteger X, IInteger Y) throws Exception
	{
		if (X == null || Y == null)
			throw new IntegerEqualizerException("A null IInteger variable was passed to constructor.");

		this.setClauses(new Conjunction(new NaturalNumberEqualizer(X.getAbsValue(), Y.getAbsValue()),
				new BitEqualizer(X.getSign(), Y.getSign())).getClauses());
	}

	public IntegerEqualizer(IInteger intgr, INaturalNumber nn) throws Exception
	{
		this.setClauses(new Conjunction(new NaturalNumberEqualizer(intgr.getAbsValue(), nn),
				new BitFixer(intgr.getSign(), true)).getClauses());
	}
}
