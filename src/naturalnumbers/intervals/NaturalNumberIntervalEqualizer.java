package naturalnumbers.intervals;

import bits.Conjunction;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.NaturalNumberEqualizer;
import naturalnumbers.intervals.exceptions.NaturalNumberIntervalEqualizerException;

public class NaturalNumberIntervalEqualizer extends Problem implements IProblem
{
	public NaturalNumberIntervalEqualizer(INaturalNumberInterval A,
			INaturalNumberInterval B) throws Exception
	{
		if (A == null || B == null)
			throw new NaturalNumberIntervalEqualizerException(
					"A null INaturalNumberInterval was passed to constructor.");
		IProblem p = new Conjunction(
				new NaturalNumberEqualizer(A.getLower(), B.getLower()),
				new NaturalNumberEqualizer(A.getUpper(), B.getUpper()));
		this.setClauses(p.getClauses());
	}
}
