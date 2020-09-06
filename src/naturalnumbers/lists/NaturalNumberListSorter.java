package naturalnumbers.lists;

import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.NaturalNumberOrderer;
import naturalnumbers.lists.exceptions.NaturalNumberListException;

/**
 * An extension of the Problem class which imposes a sort relationship on
 * INaturalNumberList source and INaturalNumberList target. Specifically, the
 * instance new NaturalNumberListSorter(source, target) is satisfied if and only
 * if source and target have the same membership and the members of target are
 * in sorted order. Equality is defined through the NaturalNumberListEqualizer
 * class. The sort is defined through the NaturalNumberOrderer class.
 * 
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Jun 2, 2004
 */
public class NaturalNumberListSorter extends Problem implements IProblem
{
	public NaturalNumberListSorter(INaturalNumberList source) throws Exception
	{
		if (source == null)
			throw new NaturalNumberListException(
					"Passed a null INaturalNumberList to constructor.");

		// The source NaturalNumberList must be in sorted order:
		IProblem[] order = new NaturalNumberOrderer[source.size() - 1];
		for (int i = 0; i < source.size() - 1; i++)
		{
			INaturalNumber before = source.getNaturalNumber(i);
			INaturalNumber after = source.getNaturalNumber(i + 1);
			order[i] = new NaturalNumberOrderer(before, after);
		}
		IProblem problem = new Conjunction(order);
		this.setClauses(problem.getClauses());
	}

	public NaturalNumberListSorter(INaturalNumberList source,
			INaturalNumberList target) throws Exception
	{
		if (source == null)
			throw new NaturalNumberListException(
					"Passed a null INaturalNumberList to constructor.");
		if (target == null)
			throw new NaturalNumberListException(
					"Passed a null INaturalNumberList to constructor.");

		IProblem problem = null;
		// The source and target NaturalNumberLists must have exactly the same
		// elements:
		IProblem same = new NaturalNumberListEqualizer(source, target);

		// The target NaturalNumberList must be in sorted order:
		IProblem[] order = new NaturalNumberOrderer[target.size() - 1];
		for (int i = 0; i < target.size() - 1; i++)
		{
			INaturalNumber before = target.getNaturalNumber(i);
			INaturalNumber after = target.getNaturalNumber(i + 1);
			order[i] = new NaturalNumberOrderer(before, after);
		}
		problem = new Conjunction(same, new Conjunction(order));
		this.setClauses(problem.getClauses());
	}
}
