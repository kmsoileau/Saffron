package sets;

import bits.BitOrderer;
import bits.Conjunction;
import bits.IProblem;
import bits.Problem;
import exceptions.sets.SubsetterException;

public class Subsetter extends Problem implements IProblem
{
	public Subsetter(Set X, Set Y) throws Exception
	{
		if (X == null || Y == null)
			throw new SubsetterException(
					"Null passed to constructor as Set parameter.");

		IProblem[] nd = new IProblem[Set.getSetSupportSize()];
		for (int i = 0; i < Set.getSetSupportSize(); i++)
		{
			nd[i] = new BitOrderer(X.getBooleanVariable(i),
					Y.getBooleanVariable(i));
		}

		this.setClauses(new Conjunction(nd).getClauses());
	}
}
