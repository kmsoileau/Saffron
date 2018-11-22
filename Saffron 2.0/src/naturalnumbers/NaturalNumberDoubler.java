package naturalnumbers;

/**
 * <p>Title: TBS</p>
 * <p>Description: TBS</p>
 * <p>Copyright (c) 2004</p>
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

public class NaturalNumberDoubler extends Problem implements IProblem
{
	public NaturalNumberDoubler(INaturalNumber X, INaturalNumber Z)
			throws Exception
	{
		IProblem p = new BitFixer(Z.getBooleanVariable(0), false);
		for (int i = 0; i < NaturalNumber.getLength() - 1; i++)
		{
			p = new Conjunction(p, new BitEqualizer(
					Z.getBooleanVariable(i + 1), X.getBooleanVariable(i)));
		}
		p = new Conjunction(p, new BitFixer(X.getBooleanVariable(NaturalNumber
				.getLength() - 1), false));
		this.setClauses(p.getClauses());
	}
}