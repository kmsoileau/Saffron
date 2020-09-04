package naturalnumbers;

import bits.BitEqualizer;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.exceptions.NaturalNumberEqualizerException;

/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 3, 2005
 */
public class NaturalNumberEqualizer extends Problem implements IProblem
{
	public NaturalNumberEqualizer(INaturalNumber X, INaturalNumber Y)
			throws Exception
	{
		if (X == null || Y == null)
			throw new NaturalNumberEqualizerException(
					"A null INaturalNumber was passed to constructor.");

		IProblem[] thba = new Problem[NaturalNumber.getLength()];
		for (int i = 0; i < NaturalNumber.getLength(); i++)
			thba[i] = new BitEqualizer(X.getBooleanVariable(i),
					Y.getBooleanVariable(i));

		IProblem p1 = new Conjunction(thba);
		this.setClauses(p1.getClauses());
	}
}
