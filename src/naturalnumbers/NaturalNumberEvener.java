package naturalnumbers;

/**
 * <p>Copyright (c) 2005 Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

public class NaturalNumberEvener extends Problem implements IProblem
{
	public NaturalNumberEvener(INaturalNumber Z) throws Exception
	{
		INaturalNumber Two = new NaturalNumber();
		INaturalNumber K = new NaturalNumber();

		IProblem p = new Conjunction(new NaturalNumberFixer(Two, 2),
				new NaturalNumberMultiplier(Two, K, Z));

		this.setClauses(p.getClauses());
	}
}