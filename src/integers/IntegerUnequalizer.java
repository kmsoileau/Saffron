/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 10, 2019
 */
package integers;

import bits.BitUnequalizer;
import bits.Conjunction;
import bits.Disjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.NaturalNumberEqualizer;
import naturalnumbers.NaturalNumberUnequalizer;

/**
 * 
 *
 */
public class IntegerUnequalizer extends Problem implements IProblem
{
	public IntegerUnequalizer(IInteger X, IInteger Y) throws Exception
	{
		INaturalNumber xabs = X.getAbsValue();
		INaturalNumber yabs = Y.getAbsValue();

		// (xabs != yabs)||(xabs==yabs && xsign!=ysign)
		this.setClauses(new Disjunction(new NaturalNumberUnequalizer(xabs, yabs),
				new Conjunction(new NaturalNumberEqualizer(xabs, yabs), new BitUnequalizer(X.getSign(), Y.getSign())))
						.getClauses());
	}
}
