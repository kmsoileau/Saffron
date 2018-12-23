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

public class NaturalNumberLeastCommonMultipler extends Problem implements
		IProblem
{
	public NaturalNumberLeastCommonMultipler(INaturalNumber M,
			INaturalNumber N, INaturalNumber LCM) throws Exception
	{
		INaturalNumber K = new NaturalNumber();
		INaturalNumber L = new NaturalNumber();

		IProblem p = new Conjunction(new NaturalNumberMultiplier(M, K, LCM),
				new NaturalNumberMultiplier(N, L, LCM),
				new NaturalNumberRelativelyPrimer(K, L));

		this.setClauses(p.getClauses());
	}
}