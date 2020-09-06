package naturalnumbers.intervals;

import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.NaturalNumberSubtracter;

/**
 * An extension of the <code>Problem</code> class which imposes a relation on an
 * <code>INaturalNumberInterval</code>. For example, the <code>Problem</code>
 * instance <code>p</code> defined by
 *
 * <p>
 * <code>IProblem p=new NaturalNumberIntervalSizer(A, 3);</code>
 * </p>
 *
 * is satisfied if and only if the interval <code>A</code> has exactly 3
 * members, i.e. it is of the form <code>{a, a+1, a+2}</code>.
 * <p>
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2018/12/01
 */
public class NaturalNumberIntervalSizer extends Problem implements IProblem
{
	public NaturalNumberIntervalSizer(INaturalNumberInterval N, long size)
			throws Exception
	{
		INaturalNumber diffNN = new NaturalNumber();

		this.setClauses(new Conjunction(
				new NaturalNumberFixer(diffNN, size - 1),
				new NaturalNumberSubtracter(N.getUpper(), N.getLower(), diffNN))
				.getClauses());
	}
}
