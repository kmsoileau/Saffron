package bitstringlists;

import bits.Conjunction;
import bits.IProblem;
import bits.Problem;
import exceptions.bitstringlists.BitStringListException;

public class BitStringListPopulator extends Problem implements IProblem
{
	public BitStringListPopulator(IBitStringList source, IBitStringList target)
			throws Exception
	{
		if (source == null)
			throw new BitStringListException(
					"Passed a null IBitStringList to constructor.");
		if (target == null)
			throw new BitStringListException(
					"Passed a null IBitStringList to constructor.");

		IProblem res[] = new IProblem[source.size()];
		for (int i = 0; i < source.size(); i++)
			res[i] = new BitStringListMembershipper(source.getBitString(i),
					target);
		this.setClauses(new Conjunction(res).getClauses());
	}
}
