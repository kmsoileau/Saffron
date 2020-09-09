package naturalnumbers.lists;

import bits.Conjunction;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.NaturalNumberXorer;
import naturalnumbers.lists.exceptions.NaturalNumberListException;
import naturalnumbers.lists.exceptions.NaturalNumberListXorerException;

public class NaturalNumberListXorer extends Problem implements IProblem
{
	public NaturalNumberListXorer(INaturalNumberList addend1, INaturalNumberList addend2, INaturalNumberList sum)
			throws NaturalNumberListException, Exception
	{
		if (addend1.size() != addend2.size() || addend1.size() != sum.size())
			throw new NaturalNumberListXorerException(
					"Size mismatch of INaturalNumberList parameters passed to constructor.");
		IProblem[] p = new Problem[addend1.size()];
		for (int i = 0; i < p.length; i++)
			p[i] = new NaturalNumberXorer(addend1.getNaturalNumber(i), addend2.getNaturalNumber(i),
					sum.getNaturalNumber(i));
		this.setClauses(new Conjunction(p).getClauses());
	}
}
