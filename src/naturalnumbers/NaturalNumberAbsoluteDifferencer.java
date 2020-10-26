package naturalnumbers;

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
 * @since Sep 09, 2020
 */
public class NaturalNumberAbsoluteDifferencer extends Problem implements IProblem
{
	// Satisfied when |X-Y|=A.
	public NaturalNumberAbsoluteDifferencer(INaturalNumber X, INaturalNumber Y, INaturalNumber A) throws Exception
	{
		this.setClauses(new Disjunction(new NaturalNumberAdder(Y, A, X), new NaturalNumberAdder(X, A, Y)).getClauses());
	}
}
