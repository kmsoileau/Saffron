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
 * @since Mar 3, 2004
 */
public class NaturalNumberDoubler extends Problem implements IProblem
{
	public NaturalNumberDoubler(INaturalNumber X, INaturalNumber Z)
			throws Exception
	{
		IProblem p = new BitFixer(Z.getBooleanVariable(0), false);
		for (int i = 0; i < NaturalNumber.getLength() - 1; i++)
		{
			p = new Conjunction(p, new BitEqualizer(
					Z.getBooleanVariable(i + 1), X.getBooleanVariable(i)));
		}
		p = new Conjunction(p, new BitFixer(X.getBooleanVariable(NaturalNumber
				.getLength() - 1), false));
		this.setClauses(p.getClauses());
	}
}