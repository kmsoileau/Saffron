package bits.strings.lists;

import bits.Conjunction;
import bits.Disjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import bits.strings.BitStringFixer;

/**
 * The IProblem o defined by
 *
 * <p>
 * BinaryBitStringListOperationer o = new BinaryBitStringListOperationer();
 * <p>
 * o.addRule(new BitString("a", "0"), new BitString("b", "0"), new
 * BitString("c", "0"));
 * <p>
 * o.addRule(new BitString("a", "0"), new BitString("b", "1"), new
 * BitString("c", "1"));
 * <p>
 * o.addRule(new BitString("a", "1"), new BitString("b", "0"), new
 * BitString("c", "1"));
 * <p>
 * o.addRule(new BitString("a", "1"), new BitString("b", "1"), new
 * BitString("c", "0"));
 * <p>
 * is satisfied if and only if
 * 
 * <p>
 * new BitStringFixer(new BitString("a", "0")) and new BitStringFixer(new
 * BitString("b", "0")) and new BitStringFixer(new BitString("c", "0")))
 * <p>
 * are all satisfied
 * <p>
 * or
 * <p>
 * new BitStringFixer(new BitString("a", "0")) and new BitStringFixer(new
 * BitString("b", "1")) and new BitStringFixer(new BitString("c", "1")))
 * <p>
 * are all satisfied
 * <p>
 * new BitStringFixer(new BitString("a", "1")) and new BitStringFixer(new
 * BitString("b", "0")) and new BitStringFixer(new BitString("c", "1")))
 * <p>
 * are all satisfied
 * <p>
 * new BitStringFixer(new BitString("a", "1")) and new BitStringFixer(new
 * BitString("b", "1")) and new BitStringFixer(new BitString("c", "0")))
 * <p>
 * are all satisfied.
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2005/04/21
 */
public class BinaryBitStringListOperationer extends Problem implements IProblem
{
	public void addRule(IBitString a, IBitString b, IBitString c)
			throws Exception
	{
		IProblem newRule = new Conjunction(new BitStringFixer(a),
				new BitStringFixer(b), new BitStringFixer(c));
		if (this.isEmpty())
			this.setClauses(newRule.getClauses());
		else
			this.setClauses(new Disjunction(this, newRule).getClauses());
	}
}
