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

public class NaturalNumberRightShifter extends Problem implements IProblem
{
	public NaturalNumberRightShifter(INaturalNumber X, INaturalNumber Z)
			throws Exception
	{
		int span = NaturalNumber.getLength();
		BitEqualizer[] be = new BitEqualizer[span - 1];
		for (int i = 0; i < span - 1; i++)
			be[i] = new BitEqualizer(Z.getBooleanVariable(i),
					X.getBooleanVariable(i + 1));
		BitFixer beset = new BitFixer(Z.getBooleanVariable(span - 1), false);
		IProblem p = new Conjunction(new Conjunction(be), beset);

		this.setClauses(p.getClauses());
	}
}