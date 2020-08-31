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
 * @since Mar 3, 2005
 */
public class NaturalNumberComparer extends Problem implements IProblem
{
	// Satisfied when X is STRICTLY LESS THAN Y.
	public NaturalNumberComparer(INaturalNumber X, INaturalNumber Y)
			throws Exception
	{
		INaturalNumber Z = new NaturalNumber();
		this.setClauses(new Conjunction(new NaturalNumberAdder(X, Z, Y),
				new NaturalNumberPositiver(Z)).getClauses());
	}
}
