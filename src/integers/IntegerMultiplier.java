/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 13, 2019
 */
package integers;

import bits.BitFixer;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.NaturalNumberMultiplier;

public class IntegerMultiplier extends Problem implements IProblem
{
	public IntegerMultiplier(IInteger X, IInteger Y, IInteger Z) throws Exception
	{
		INaturalNumber Xabs = X.getAbsValue();
		IBooleanVariable Xsign = X.getSign();

		INaturalNumber Yabs = Y.getAbsValue();
		IBooleanVariable Ysign = Y.getSign();

		INaturalNumber Zabs = Z.getAbsValue();
		IBooleanVariable Zsign = Z.getSign();

		// if ((Xsign=true && Ysign=true) or (Xsign=false && Ysign=false)) then
		// Zsign=true
		// if ((Xsign=false && Ysign=true) or (Xsign=true && Ysign=false)) then
		// Zsign=false
		// Zabs=Xabs * Yabs

		IProblem problem = new Conjunction(
				new Disjunction(
						new Conjunction(new Disjunction(new BitFixer(Xsign, false), new BitFixer(Ysign, false)),
								new Disjunction(new BitFixer(Xsign, true), new BitFixer(Ysign, true))),
						new BitFixer(Zsign, true)),
				new Disjunction(
						new Conjunction(new Disjunction(new BitFixer(Xsign, true), new BitFixer(Ysign, false)),
								new Disjunction(new BitFixer(Xsign, false), new BitFixer(Ysign, true))),
						new BitFixer(Zsign, false)),
				new NaturalNumberMultiplier(Xabs, Yabs, Zabs));

		this.setClauses(problem.getClauses());
	}
}