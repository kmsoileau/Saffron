package naturalnumbers.lists;

import bits.Disjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.NaturalNumberEqualizer;
import naturalnumbers.lists.exceptions.NaturalNumberListMembershipException;

/**
 * <p>
 * Copyright (c) 2005 Positronic Software
 * </p>
 * 
 * @author Kerry Michael Soileau
 * @version 1.0
 */

public class NaturalNumberListMembership extends Problem implements IProblem
{
	public NaturalNumberListMembership(INaturalNumber inn,
			INaturalNumberList bsl) throws Exception
	{
		if (bsl == null)
			throw new NaturalNumberListMembershipException(
					"Passed a null INaturalNumberList to constructor.");
		if (inn == null)
			throw new NaturalNumberListMembershipException(
					"Passed a null INaturalNumber to constructor.");
		IProblem[] res = new IProblem[bsl.size()];
		for (int i = 0; i < bsl.size(); i++)
		{
			INaturalNumber curr = bsl.getNaturalNumber(i);
			res[i] = new NaturalNumberEqualizer(curr, inn);
		}
		IProblem problem = new Disjunction(res);
		this.setClauses(problem.getClauses());
	}
}