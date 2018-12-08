package sets;

import java.util.HashMap;

import bits.BitFixer;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;
import exceptions.sets.SetIntersectorException;

/**
 * Z = X ∩ Y
 */
public class SetIntersector extends Problem implements IProblem
{
	public SetIntersector(Set X, Set Y, Set Z) throws Exception
	{
		if(X==null || Y==null || Z==null)
			throw new SetIntersectorException(
					"Null passed to constructor as Set parameter.");
		HashMap<Object, IBooleanVariable> xBacking = X.getBacking();
		if (xBacking == null)
			throw new SetIntersectorException(
					"Set with null backing passed to constructor.");
		java.util.Set<Object> xSupport = X.getSupport();
		if (xSupport == null)
			throw new SetIntersectorException(
					"Set with null keySet passed to constructor.");
		HashMap<Object, IBooleanVariable> yBacking = Y.getBacking();
		if (yBacking == null)
			throw new SetIntersectorException(
					"Set with null backing passed to constructor.");
		java.util.Set<Object> ySupport = Y.getSupport();
		if (ySupport == null)
			throw new SetIntersectorException(
					"Set with null keySet passed to constructor.");
		HashMap<Object, IBooleanVariable> zBacking = Z.getBacking();
		if (zBacking == null)
			throw new SetIntersectorException(
					"Set with null backing passed to constructor.");
		java.util.Set<Object> zSupport = Z.getSupport();
		if (zSupport == null)
			throw new SetIntersectorException(
					"Set with null keySet passed to constructor.");

		if (xBacking.size() == 0 || yBacking.size() == 0)
			this.setClauses(Problem.unsolvableProblem().getClauses());
		else
		{
			IProblem problem = Problem.newProblem();
			for (Object o : xSupport)
			{
				if (ySupport.contains(o))
				{
					zSupport.add(o);
					problem = new Conjunction(problem, new Disjunction(
							new BitFixer(X.contains(o), false), new BitFixer(
									Y.contains(o), false), new BitFixer(
									Z.contains(o), true)));
				}
			}
			this.setClauses(problem.getClauses());
		}
	}
}
