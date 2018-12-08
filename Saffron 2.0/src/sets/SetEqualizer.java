package sets;

import exceptions.sets.SetEqualizerException;
import bits.Conjunction;
import bits.IProblem;
import bits.Problem;

public class SetEqualizer extends Problem implements IProblem
{
	public SetEqualizer(Set X, Set Y) throws Exception
	{
		if(X==null || Y==null)
			throw new SetEqualizerException("Null passed to constructor as Set parameter.");
		this.setClauses(new Conjunction(new Subsetter(X, Y),
				new Subsetter(Y, X)).getClauses());
	}
}
