package naturalnumbers.lists;

import bits.Conjunction;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.lists.exceptions.NaturalNumberListEqualizerException;

/**
 * Note that for two INaturalNumberLists to be equal, in the sense enforced by
 * this IProblem, means for their contents to be equal. This does not enforce
 * that the two INaturalNumberLists present their contents in the same order.
 */

public class NaturalNumberListEqualizer extends Problem implements IProblem
{
	public NaturalNumberListEqualizer(INaturalNumberList A, INaturalNumberList B) throws Exception
	{
		if (A == null)
			throw new NaturalNumberListEqualizerException("Passed a null A to constructor.");
		if (B == null)
			throw new NaturalNumberListEqualizerException("Passed a null B to constructor.");
		IProblem problem = new Conjunction(new NaturalNumberListSubsetter(A, B), new NaturalNumberListSubsetter(B, A));
		this.setClauses(problem.getClauses());
	}

	/*
	 * public NaturalNumberListEqualizer(INaturalNumberList a, INaturalNumberList b)
	 * throws Exception { if(a.size()!=b.size()) throw new
	 * NaturalNumberListEqualizerException
	 * ("Size mismatch of INaturalNumberList parameters passed to constructor." );
	 * IProblem[] p=new Problem[a.size()]; for(int i=0;i<p.length;i++) p[i]=new
	 * NaturalNumberEqualizer(a.getNaturalNumber(i),b.getNaturalNumber(i));
	 * this.setClauses(new Conjunction(p).getClauses()); }
	 */
}
