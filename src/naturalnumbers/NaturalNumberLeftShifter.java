package naturalnumbers;

import bits.BitEqualizer;
import bits.BitFixer;
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
 * @since Mar 3, 2005
 */
public class NaturalNumberLeftShifter extends Problem implements IProblem
{
	public NaturalNumberLeftShifter(INaturalNumber X, INaturalNumber Z)
			throws Exception
	{
		int span = NaturalNumber.getLength();
		BitEqualizer[] be = new BitEqualizer[span - 1];
		for (int i = 0; i < span - 1; i++)
			be[i] = new BitEqualizer(Z.getBooleanVariable(i + 1),
					X.getBooleanVariable(i));
		BitFixer beset = new BitFixer(Z.getBooleanVariable(0), false);
		IProblem p = new Conjunction(new Conjunction(be), beset);

		this.setClauses(p.getClauses());
	}
}
