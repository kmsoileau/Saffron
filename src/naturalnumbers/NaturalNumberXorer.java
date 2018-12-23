package naturalnumbers;

/**
 * <p>Title: TBS</p>
 * <p>Description: TBS</p>
 * <p>Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */

import bits.BitXorer;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

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