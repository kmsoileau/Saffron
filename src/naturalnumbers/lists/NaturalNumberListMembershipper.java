package naturalnumbers.lists;

import bits.Disjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.NaturalNumberEqualizer;
import naturalnumbers.lists.exceptions.NaturalNumberListException;

public class NaturalNumberListMembershipper extends Problem implements IProblem
{
	public NaturalNumberListMembershipper(INaturalNumber element, INaturalNumberList set) throws Exception
	{
		if (set == null)
			throw new NaturalNumberListException("Passed a null INaturalNumberList to constructor.");
		if (element == null)
			throw new NaturalNumberListException("Passed a null INaturalNumber to constructor.");
		IProblem[] res = new IProblem[set.size()];
		for (int i = 0; i < set.size(); i++)
		{
			INaturalNumber curr = set.getNaturalNumber(i);
			res[i] = new NaturalNumberEqualizer(curr, element);
		}
		IProblem problem = new Disjunction(res);
		this.setClauses(problem.getClauses());
	}
}
