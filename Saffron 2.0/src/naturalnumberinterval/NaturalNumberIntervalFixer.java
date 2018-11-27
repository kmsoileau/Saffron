package naturalnumberinterval;

import naturalnumbers.NaturalNumberFixer;
import bits.Conjunction;
import bits.IProblem;
import bits.Problem;

public class NaturalNumberIntervalFixer extends Problem implements IProblem
{
	public NaturalNumberIntervalFixer(INaturalNumberInterval N, long a, long b)
			throws Exception
	{
		this.setClauses(new Conjunction(
				new NaturalNumberFixer(N.getLower(), a),
				new NaturalNumberFixer(N.getUpper(), b)).getClauses());
	}
}
