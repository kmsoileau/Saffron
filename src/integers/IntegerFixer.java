package integers;

import bits.BitFixer;
import bits.Conjunction;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.NaturalNumberFixer;

/**
 * 
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2019/02/10
 */
public class IntegerFixer extends Problem implements IProblem
{
	public IntegerFixer(IInteger ntgr) throws Exception
	{
		this.setClauses(
				new Conjunction(new NaturalNumberFixer(ntgr.getAbsValue()), new BitFixer(ntgr.getSign())).getClauses());
	}

	public IntegerFixer(IInteger ntgr, long value) throws Exception
	{
		this.setClauses(new Conjunction(new NaturalNumberFixer(ntgr.getAbsValue(), Math.abs(value)),
				new BitFixer(ntgr.getSign(), value >= 0)).getClauses());
	}

	public IntegerFixer(IInteger[] d) throws Exception
	{
		IProblem[] p = new IProblem[d.length];

		for (int i = 0; i < d.length; i++)
			p[i] = new IntegerFixer(d[i]);

		this.setClauses(new Conjunction(p).getClauses());
	}
}