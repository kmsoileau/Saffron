package naturalnumbers;

import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

public class NaturalNumberBounder extends Problem implements IProblem
{
	public NaturalNumberBounder(INaturalNumber X, long bound) throws Exception
	{
		NaturalNumber bnd = new NaturalNumber(bound);
		this.setClauses(new Conjunction(new NaturalNumberFixer(bnd), new NaturalNumberOrderer(X, bnd)).getClauses());
	}
}