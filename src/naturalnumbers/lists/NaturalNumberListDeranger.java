package naturalnumbers.lists;

import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.NaturalNumberUnequalizer;
import naturalnumbers.lists.exceptions.NaturalNumberListDerangerException;

/*
 * NaturalNumberListDeranger.java	1.0 09/05/21
 *
 * Copyright 2009 Positronic Software.
 *
 */
/**
 * An extension of the Problem class which imposes a derangement relationship on
 * INaturalNumberList source and INaturalNumberList target. Specifically, the
 * instance new NaturalNumberListSorter(source, target) is satisfied if and only
 * if source and target have the same membership and the members at each list
 * position, the source entry is not equal to the target entry. Note: All of the
 * NaturalNumbers in the source list must have the same length, and there should
 * be a corresponding allocation for the NaturalNumbers in the target.
 *
 * @author Kerry Michael Soileau
 * 
 *         <pre>
 * ksoileau2@yahoo.com
 * http://kerrysoileau.com/index.htm
 * </pre>
 * @version 1.0, 2009/05/21
 */

public class NaturalNumberListDeranger extends Problem implements IProblem
{
	public NaturalNumberListDeranger(INaturalNumberList source,
			INaturalNumberList target) throws Exception
	{
		if (source == null)
			throw new NaturalNumberListDerangerException(
					"Passed a null source to constructor.");
		if (target == null)
			throw new NaturalNumberListDerangerException(
					"Passed a null target to constructor.");

		IProblem problem = null;
		IProblem same = new NaturalNumberListEqualizer(source, target);
		// The two lists must be deranged:
		IProblem[] compare = new IProblem[target.size()];
		for (int i = 0; i < target.size(); i++)
		{
			INaturalNumber before = source.getNaturalNumber(i);
			INaturalNumber after = target.getNaturalNumber(i);
			compare[i] = new NaturalNumberUnequalizer(before, after);
		}
		problem = new Conjunction(same, new Conjunction(compare));
		this.setClauses(problem.getClauses());
	}
}
