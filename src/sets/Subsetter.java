package sets;

import bits.BitOrderer;
import bits.Conjunction;
import bits.IProblem;
import bits.Problem;
import sets.exceptions.SubsetterException;

public class Subsetter extends Problem implements IProblem
{
	public Subsetter(Set X, Set Y) throws Exception
	{
		if (X == null || Y == null)
			throw new SubsetterException(
					"Null passed to constructor as Set parameter.");

		int size = Set.getSetSupportSize();
		if (size == 0)
		{
			// The empty set is always a subset.
			this.setClauses(Problem.trivialProblem().getClauses());
		}
		else
		{
			IProblem[] nd = new IProblem[size];
			for (int i = 0; i < size; i++)
			{
				nd[i] = new BitOrderer(X.getBooleanVariable(i),
						Y.getBooleanVariable(i));
			}
			this.setClauses(new Conjunction(nd).getClauses());
		}
	}
}
