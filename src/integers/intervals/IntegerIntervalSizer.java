package integers.intervals;

import bits.Conjunction;
import bits.IProblem;
import bits.Problem;
import integers.IInteger;
import integers.Integer;
import integers.IntegerFixer;
import integers.IntegerSubtracter;

/**
 * An extension of the <code>Problem</code> class which imposes a relation on an
 * <code>IIntegerInterval</code>. For example, the <code>Problem</code> instance
 * <code>p</code> defined by
 *
 * <p>
 * <code>IProblem p=new IntegerIntervalSizer(A, 3);</code>
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
 * @since 2019/02/26
 */
public class IntegerIntervalSizer extends Problem implements IProblem
{
	public IntegerIntervalSizer(IIntegerInterval N, long size) throws Exception
	{
		IInteger diffNN = new Integer();

		this.setClauses(new Conjunction(new IntegerFixer(diffNN, size - 1),
				new IntegerSubtracter(N.getUpper(), N.getLower(), diffNN)).getClauses());
	}
}
