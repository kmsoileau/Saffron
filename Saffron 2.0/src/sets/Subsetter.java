package sets;

import java.util.HashMap;

import exceptions.sets.SetNonemptierException;
import bits.BitFixer;
import bits.BitOrderer;
import bits.Conjunction;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;

public class Subsetter extends Problem implements IProblem
{
	public Subsetter(Set X, Set Y) throws Exception
	{
		super();
		HashMap<Object, IBooleanVariable> xBacking = X.getBacking();
		if (xBacking == null)
			throw new SetNonemptierException(
					"Set with null backing passed to constructor.");
		java.util.Set<Object> xKeySet = xBacking.keySet();
		if (xKeySet == null)
			throw new SetNonemptierException(
					"Set with null keySet passed to constructor.");
		HashMap<Object, IBooleanVariable> yBacking = Y.getBacking();
		if (yBacking == null)
			throw new SetNonemptierException(
					"Set with null backing passed to constructor.");
		java.util.Set<Object> yKeySet = yBacking.keySet();
		if (yKeySet == null)
			throw new SetNonemptierException(
					"Set with null keySet passed to constructor.");

		if (X.getBacking().size() == 0)
			this.setClauses(Problem.trivialProblem().getClauses());
		else
		{
			IProblem problem = Problem.newProblem();

			for (Object o : xKeySet.toArray(new Object[0]))
			{
				if (!yKeySet.contains(o))
				{
					problem = new Conjunction(problem, new BitFixer(
							xBacking.get(o), false));
				}
				else
				{
					problem = new Conjunction(problem, new BitOrderer(
							xBacking.get(o), yBacking.get(o)));
				}
			}
			this.setClauses(problem.getClauses());
		}
	}
}
