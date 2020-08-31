package sets;

import sets.exceptions.SetEqualizerException;
import bits.Conjunction;
import bits.IProblem;
import bits.Problem;

public class SetEqualizer extends Problem implements IProblem
{
	public SetEqualizer(Set X, Set Y) throws Exception
	{
		if (X == null || Y == null)
			throw new SetEqualizerException(
					"Null passed to constructor as Set parameter.");
		Subsetter s1 = new Subsetter(X, Y);
		Subsetter s2 = new Subsetter(Y, X);
		this.setClauses(new Conjunction(s1, s2).getClauses());
	}
}
