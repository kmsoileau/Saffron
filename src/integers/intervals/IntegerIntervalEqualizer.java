package integers.intervals;

import bits.Conjunction;
import bits.IProblem;
import bits.Problem;
import integers.IntegerEqualizer;
import integers.intervals.exceptions.IntegerIntervalEqualizerException;

public class IntegerIntervalEqualizer extends Problem implements IProblem
{
	public IntegerIntervalEqualizer(IIntegerInterval A, IIntegerInterval B) throws Exception
	{
		if (A == null || B == null)
			throw new IntegerIntervalEqualizerException("A null IIntegerInterval was passed to constructor.");
		IProblem p = new Conjunction(new IntegerEqualizer(A.getLower(), B.getLower()),
				new IntegerEqualizer(A.getUpper(), B.getUpper()));
		this.setClauses(p.getClauses());
	}
}
