package naturalnumbers;

/**
 * <p>Title: TBS</p>
 * <p>Description: TBS</p>
 * <p>Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */

import bits.BitEqualizer;
import bits.BitFixer;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

public class NaturalNumberLeftShifter extends Problem implements IProblem
{
	public NaturalNumberLeftShifter(INaturalNumber X, INaturalNumber Z)
			throws Exception
	{
		int span = NaturalNumber.getLength();
		BitEqualizer[] be = new BitEqualizer[span - 1];
		for (int i = 0; i < span - 1; i++)
			be[i] = new BitEqualizer(Z.getBooleanVariable(i + 1),
					X.getBooleanVariable(i));
		BitFixer beset = new BitFixer(Z.getBooleanVariable(0), false);
		IProblem p = new Conjunction(new Conjunction(be), beset);

		this.setClauses(p.getClauses());
	}
}