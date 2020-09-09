package naturalnumbers;

import bits.BitFixer;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBooleanVariable;
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
public class NaturalNumberFromBitConverter extends Problem implements IProblem
{
	public NaturalNumberFromBitConverter(INaturalNumber b, IBooleanVariable bv) throws Exception
	{
		IProblem convert = new Disjunction(new Conjunction(new NaturalNumberFixer(b, 0L), new BitFixer(bv, false)),
				new Conjunction(new NaturalNumberFixer(b, 1L), new BitFixer(bv, true)));

		this.setClauses(convert.getClauses());
	}
}