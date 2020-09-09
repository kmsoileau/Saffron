package naturalnumbers;

import bits.BitNoter;
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
public class NaturalNumberNoter extends Problem implements IProblem
{
	public NaturalNumberNoter(INaturalNumber X, INaturalNumber Y) throws Exception
	{
		BitNoter[] bnnb = new BitNoter[NaturalNumber.getLength()];
		for (int i = 0; i < bnnb.length; i++)
			bnnb[i] = new BitNoter(X.getBooleanVariable(i), Y.getBooleanVariable(i));
		IProblem p = new Conjunction(bnnb);
		this.setClauses(p.getClauses());
	}
}