/*
 * BitStringListSorter.java	1.0 05/05/04
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */
/**
 * An extension of the Problem class which imposes a sort relationship on
 * IBitStringList source and IBitStringList target. Specifically, the instance
 * new BitStringListSorter(source, target) is satisfied if and only if source
 * and target have the same membership and the members of target are in sorted
 * order. Equality is defined through the BitStringListEqualizer class.
 * The sort is defined through the BitStringOrderer class.
 *
 * @author  Kerry Michael Soileau
 * <blockquote><pre>
 * ksoileau2@yahoo.com
 * http://kerrysoileau.com/index.html
 * </pre></blockquote>
 * @version 1.0, 05/05/04
 * @see BitStringException
 * @see BitStringListEqualizer
 * @see BitStringListException
 * @see BitStringOrderer
 * @see BooleanLiteralException
 * @see Conjunction
 * @see IBitString
 * @see IBitStringList
 */

package bitstringlists;

import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import bitstrings.BitStringOrderer;
import exceptions.bits.BooleanLiteralException;
import exceptions.bitstringlists.BitStringListException;
import exceptions.bitstrings.BitStringException;

@SuppressWarnings("unused")
public class BitStringListSorter extends Problem implements IProblem
{
	public BitStringListSorter(IBitStringList source) throws Exception
	{
		if (source == null)
			throw new BitStringListException(
					"Passed a null IBitStringList to constructor.");

		// The source BitStringList must be in sorted order:
		IProblem[] order = new BitStringOrderer[source.size() - 1];
		for (int i = 0; i < source.size() - 1; i++)
		{
			IBitString before = source.getBitString(i);
			IBitString after = source.getBitString(i + 1);
			order[i] = new BitStringOrderer(before, after);
		}
		IProblem problem = new Conjunction(order);
		this.setClauses(problem.getClauses());
	}

	public BitStringListSorter(IBitStringList source, IBitStringList target)
			throws Exception
	{
		if (source == null)
			throw new BitStringListException(
					"Passed a null IBitStringList to constructor.");
		if (target == null)
			throw new BitStringListException(
					"Passed a null IBitStringList to constructor.");

		IProblem problem = null;
		// The source and target BitStringLists must have exactly the same
		// elements:
		IProblem same = new BitStringListEqualizer(source, target);
		// The target BitStringList must be in sorted order:
		IProblem[] order = new BitStringOrderer[target.size() - 1];
		for (int i = 0; i < target.size() - 1; i++)
		{
			IBitString before = target.getBitString(i);
			IBitString after = target.getBitString(i + 1);
			order[i] = new BitStringOrderer(before, after);
		}
		problem = new Conjunction(same, new Conjunction(order));
		this.setClauses(problem.getClauses());
	}
}