package sets;

import java.util.HashMap;

import bits.Conjunction;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;

public class SetEqualizer extends Problem implements IProblem
{
	public SetEqualizer(Set X, Set Y) throws Exception
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

		this.setClauses(new Conjunction(new Subsetter(X, Y),
				new Subsetter(Y, X)).getClauses());

	}
}
