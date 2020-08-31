package in_development;

import bits.BitFixer;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;
import bits.ProblemDenier;

/**
 * This class links the value of an IBooleanVariable to the truth value of a
 * given IProblem.
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2005/12/26
 */
public class ProblemBitLinker extends Problem implements IProblem
{
	public ProblemBitLinker(IProblem p, IBooleanVariable b) throws Exception
	{
		IProblem pl = new Conjunction(p, new BitFixer(b, true));
		IProblem pr = new Conjunction(new ProblemDenier(p), new BitFixer(b,
				false));
		IProblem result = new Disjunction(pl, pr);

		this.setClauses(result.getClauses());
	}
}
