/*
 * NaturalNumberListJoiner.java	1.0 06/02/10
 *
 * Copyright 2006 Positronic Software.
 *
 *
 */

package naturalnumbers.lists;

import java.util.ArrayList;

import bits.Conjunction;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.NaturalNumberEqualizer;
import naturalnumbers.lists.exceptions.NaturalNumberListException;

public class NaturalNumberListJoiner extends Problem implements IProblem
{
	public NaturalNumberListJoiner(INaturalNumberList join,
			INaturalNumberList A, INaturalNumberList B) throws Exception
	{
		if (join == null || A == null || B == null)
			throw new NaturalNumberListException(
					"Passed null INaturalNumberList to NaturalNumberListJoiner constructor.");
		if (join.size() != A.size() + B.size())
			throw new NaturalNumberListException(
					"INaturalNumberList join is incorrect size to hold the INaturalNumberList formed by the concatenation of INaturalNumberLists A and B.");

		ArrayList<IProblem> p = new ArrayList<IProblem>();
		for (int i = 0; i < A.size(); i++)
			p.add(new NaturalNumberEqualizer(A.getNaturalNumber(i), join
					.getNaturalNumber(i)));
		for (int i = A.size(); i < A.size() + B.size(); i++)
			p.add(new NaturalNumberEqualizer(B.getNaturalNumber(i - A.size()),
					join.getNaturalNumber(i)));

		this.setClauses(new Conjunction(p).getClauses());
	}
}