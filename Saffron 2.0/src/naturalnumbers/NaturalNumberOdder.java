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

public class NaturalNumberOdder extends Problem implements IProblem
{
	public NaturalNumberOdder(INaturalNumber Z) throws Exception
	{
		INaturalNumber One = new NaturalNumber();
		INaturalNumber Two = new NaturalNumber();
		INaturalNumber K = new NaturalNumber();
		INaturalNumber L = new NaturalNumber();

		IProblem p = new Conjunction(new NaturalNumberFixer(One, 1),
				new NaturalNumberFixer(Two, 2), new NaturalNumberMultiplier(
						Two, K, L), new NaturalNumberIncrementer(L, Z));

		this.setClauses(p.getClauses());
	}
}