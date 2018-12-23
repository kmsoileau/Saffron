package naturalnumberintervals;

import bits.BitFixer;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;
import exceptions.naturalnumberintervals.NaturalNumberIntervalDisjointSelectorException;

/**
 * An extension of the <code>Problem</code> class which imposes a relation on a
 * set of <code>INaturalNumberInterval</code>s. For example, for
 * <code>INaturalNumberInterval[] I</code> and
 * <code>IBooleanVariable[] bool</code> the <code>Problem</code> instance
 * <code>p</code> defined by
 *
 * <p>
 * <code>IProblem p=new NaturalNumberIntervalDisjointSelector(I,bool)</code>
 * </p>
 *
 * is satisfied if and only if for every <code>i</code>, <code>bool[i]</code> is
 * true and <code>bool[j]</code> is true implies the intervals <code>I[i]</code>
 * and <code>I[j]</code> have no members in common.
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
public class NaturalNumberIntervalDisjointSelector extends Problem implements
		IProblem
{
	public NaturalNumberIntervalDisjointSelector(INaturalNumberInterval[] I,
			IBooleanVariable[] bool) throws Exception
	{
		if (I == null || bool == null)
			throw new NaturalNumberIntervalDisjointSelectorException(
					"A null array was passed to constructor");
		if (I.length != bool.length)
			throw new NaturalNumberIntervalDisjointSelectorException(
					"Arrays of unequal size passed to constructor");

		int stagingIndex = 0;
		IProblem[] stagingArray = new IProblem[I.length * I.length];
		for (int i = 0; i < I.length; i++)
			for (int j = i + 1; j < I.length; j++)
			{
				IProblem curr = new Disjunction(new BitFixer(bool[i], false),
						new BitFixer(bool[j], false),
						new NaturalNumberIntervalDisjointer(I[i], I[j]));
				stagingArray[stagingIndex++] = curr;
			}

		this.setClauses(new Conjunction(stagingArray).getClauses());
	}
}