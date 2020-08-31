package naturalnumbers;

import bits.BitXorer;
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
 * @since Mar 4, 2005
 */
public class NaturalNumberXorer extends Problem implements IProblem
{
	public NaturalNumberXorer(INaturalNumber X, INaturalNumber Y,
			INaturalNumber Z) throws Exception
	{
		IProblem[] bnnb = new BitXorer[NaturalNumber.getLength()];
		for (int i = 0; i < bnnb.length; i++)
			bnnb[i] = new BitXorer(X.getBooleanVariable(i),
					Y.getBooleanVariable(i), Z.getBooleanVariable(i));
		IProblem p = new Conjunction(bnnb);
		this.setClauses(p.getClauses());
	}
}