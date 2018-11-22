package naturalnumbers;

/**
 * <p>Title: TBS</p>
 * <p>Description: TBS</p>
 * <p>Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

public class NaturalNumberGreatestCommonDivisor extends Problem implements
		IProblem
{
	public NaturalNumberGreatestCommonDivisor(INaturalNumber M,
			INaturalNumber N, INaturalNumber GCD) throws Exception
	{
		INaturalNumber K = new NaturalNumber();
		INaturalNumber L = new NaturalNumber();

		IProblem p = new Conjunction(new NaturalNumberMultiplier(GCD, K, M),
				new NaturalNumberMultiplier(GCD, L, N),
				new NaturalNumberRelativelyPrimer(K, L));

		this.setClauses(p.getClauses());
	}
}