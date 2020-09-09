package naturalnumbers.lists;

import bits.Conjunction;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.lists.exceptions.NaturalNumberListException;

public class NaturalNumberListSubsetter extends Problem implements IProblem
{
	// Ensures that every INaturalNumber in A is also in B
	public NaturalNumberListSubsetter(INaturalNumberList A, INaturalNumberList B) throws Exception
	{
		if (A == null)
			throw new NaturalNumberListException("Passed a null INaturalNumberList to constructor.");
		if (B == null)
			throw new NaturalNumberListException("Passed a null INaturalNumberList to constructor.");

		IProblem res[] = new IProblem[A.size()];
		for (int i = 0; i < A.size(); i++)
			res[i] = new NaturalNumberListMembershipper(A.getNaturalNumber(i), B);
		IProblem total = new Conjunction(res);
		total = new Conjunction(total, new NaturalNumberListNonrepeater(A), new NaturalNumberListNonrepeater(B));
		this.setClauses(total.getClauses());
	}
}
