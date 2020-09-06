package integers.intervals;

import bits.BitFixer;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;
import integers.intervals.exceptions.IntegerIntervalDisjointSelectorException;

/**
 * An extension of the <code>Problem</code> class which imposes a relation on a
 * set of <code>IIntegerInterval</code>s. For example, for
 * <code>IIntegerInterval[] I</code> and <code>IBooleanVariable[] bool</code>
 * the <code>Problem</code> instance <code>p</code> defined by
 *
 * <p>
 * <code>IProblem p=new IntegerIntervalDisjointSelector(I,bool)</code>
 * </p>
 *
 * is satisfied if and only if for every distinct <code>i</code> and
 * <code>j</code>, <code>bool[i]</code> is true and <code>bool[j]</code> is true
 * implies the intervals <code>I[i]</code> and <code>I[j]</code> have no members
 * in common.
 * <p>
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2018/11/29
 */
public class IntegerIntervalDisjointSelector extends Problem implements
		IProblem
{
	public IntegerIntervalDisjointSelector(IIntegerInterval[] I,
			IBooleanVariable[] bool) throws Exception
	{
		if (I == null || bool == null)
			throw new IntegerIntervalDisjointSelectorException(
					"A null array was passed to constructor");
		if (I.length != bool.length)
			throw new IntegerIntervalDisjointSelectorException(
					"Arrays of unequal size passed to constructor");

		int stagingIndex = 0;
		IProblem[] stagingArray = new IProblem[I.length * I.length];
		for (int i = 0; i < I.length; i++)
			for (int j = i + 1; j < I.length; j++)
			{
				IProblem curr = new Disjunction(new BitFixer(bool[i], false),
						new BitFixer(bool[j], false),
						new IntegerIntervalDisjointer(I[i], I[j]));
				stagingArray[stagingIndex++] = curr;
			}

		this.setClauses(new Conjunction(stagingArray).getClauses());
	}
}