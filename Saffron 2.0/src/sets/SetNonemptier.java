package sets;

import java.util.HashMap;

import exceptions.sets.SetNonemptierException;
import bits.BitFixer;
import bits.Disjunction;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;

public class SetNonemptier extends Problem implements IProblem
{
	public SetNonemptier(Set X) throws Exception
	{
		super();
		HashMap<Object, IBooleanVariable> backing = X.getBacking();
		if (backing == null)
			throw new SetNonemptierException(
					"Set with null backing passed to constructor.");
		java.util.Set<Object> keySet = backing.keySet();
		if (keySet == null)
			throw new SetNonemptierException(
					"Set with null keySet passed to constructor.");

		IProblem problem = Problem.newProblem();
		Object[] keySetArray = keySet.toArray(new Object[0]);
		for (Object o : keySetArray)
		{
			problem = new Disjunction(problem, new BitFixer(backing.get(o), true));
		}

		this.setClauses(problem.getClauses());
	}
}
