package naturalnumbers;

import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

/**
 * This problem is satisfied if there are natural numbers such that A*X-B*Y=1.
 * 
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 4, 2005
 */
public class NaturalNumberRelativelyPrimer extends Problem implements IProblem
{
	public NaturalNumberRelativelyPrimer(INaturalNumber X, INaturalNumber Y)
			throws Exception
	{
		this(X, Y, new NaturalNumber(), new NaturalNumber());
	}

	public NaturalNumberRelativelyPrimer(INaturalNumber X, INaturalNumber Y,
			INaturalNumber A, INaturalNumber B) throws Exception
	{
		INaturalNumber P = new NaturalNumber();
		INaturalNumber Q = new NaturalNumber();
		INaturalNumber One = new NaturalNumber();
		IProblem problem = new Conjunction(new IProblem[]
		{ new NaturalNumberFixer(One, 1), new NaturalNumberMultiplier(X, A, P),
				new NaturalNumberMultiplier(Y, B, Q),
				new NaturalNumberAdder(Q, One, P) });

		this.setClauses(problem.getClauses());
	}
}