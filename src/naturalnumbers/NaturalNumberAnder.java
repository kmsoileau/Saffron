package naturalnumbers;

import bits.BitAnder;
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
public class NaturalNumberAnder extends Problem implements IProblem
{
	public NaturalNumberAnder(INaturalNumber X, INaturalNumber Y,
			INaturalNumber Z) throws Exception
	{
		int span = NaturalNumber.getLength();
		BitAnder[] bnnb = new BitAnder[span];
		for (int i = 0; i < bnnb.length; i++)
			bnnb[i] = new BitAnder(X.getBooleanVariable(i),
					Y.getBooleanVariable(i), Z.getBooleanVariable(i));
		IProblem p = new Conjunction(bnnb);
		this.setClauses(p.getClauses());
	}
}
