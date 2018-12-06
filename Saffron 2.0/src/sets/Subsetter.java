package sets;

import java.util.HashMap;
import java.util.List;

import bits.BitOrderer;
import bits.Conjunction;
import bits.IBooleanVariable;
import bits.IClause;
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
		
		if(X.getBacking().size()==0)
			this.setClauses(Problem.trivialProblem().getClauses());

		IProblem problem = Problem.newProblem();

		boolean success = true;
		for (Object o : xKeySet.toArray(new Object[0]))
		{
			if (!yKeySet.contains(o))
			{
				success = false;
				break;
			}
			problem = new Conjunction(problem, new BitOrderer(xBacking.get(o),
					yBacking.get(o)));
		}
		if (success)
		{
			List<IClause> ff = Problem.trivialProblem().getClauses();
			this.setClauses(ff);
		}
		else
			this.setClauses(Problem.unsolvableProblem().getClauses());
	}
}
