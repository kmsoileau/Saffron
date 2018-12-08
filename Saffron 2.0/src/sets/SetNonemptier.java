package sets;

import java.util.HashMap;

import bits.BitFixer;
import bits.Disjunction;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;
import exceptions.sets.SetNonemptierException;

public class SetNonemptier extends Problem implements IProblem
{
	public SetNonemptier(Set X) throws Exception
	{
		HashMap<Object, IBooleanVariable> backing = X.getBacking();
		if (backing == null)
			throw new SetNonemptierException(
					"Set with null backing passed to constructor.");
		java.util.Set<Object> keySet = backing.keySet();

		IProblem problem = Problem.newProblem();
		Object[] keySetArray = keySet.toArray(new Object[0]);
		for (Object o : keySetArray)
		{
			problem = new Disjunction(problem, new BitFixer(backing.get(o), true));
		}

		this.setClauses(problem.getClauses());
	}
}
