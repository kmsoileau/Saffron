package naturalnumbers.lists;

import java.util.ArrayList;

import bits.BitFixer;
import bits.Conjunction;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.lists.exceptions.NaturalNumberListException;

public class NaturalNumberListFixer extends Problem implements IProblem
{
	public NaturalNumberListFixer(INaturalNumberList target) throws Exception
	{
		if (target == null)
			throw new NaturalNumberListException("Passed null INaturalNumberList to constructor.");
		ArrayList<IProblem> p = new ArrayList<IProblem>();
		for (int i = 0; i < target.size(); i++)
			for (int j = 0; j < target.getNaturalNumber(i).size(); j++)
			{
				IProblem bf = new BitFixer(target.getNaturalNumber(i).getBooleanVariable(j),
						target.getNaturalNumber(i).getBooleanVariable(j).getValue());
				p.add(bf);
			}
		this.setClauses(new Conjunction(p).getClauses());
	}
}