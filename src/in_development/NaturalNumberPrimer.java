package in_development;

/**
 * <p>Title: TBS</p>
 * <p>Description: TBS</p>
 * <p>Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberCompositor;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.NaturalNumberOrderer;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import bits.ProblemDenier;

public class NaturalNumberPrimer extends Problem implements IProblem
{
	public NaturalNumberPrimer(INaturalNumber Z) throws Exception
	{
		INaturalNumber Two = new NaturalNumber();
		IProblem p1 = new NaturalNumberFixer(Two, 2);
		IProblem p2 = new ProblemDenier(new NaturalNumberCompositor(Z));
		IProblem p3 = new NaturalNumberOrderer(Two, Z);

		IProblem p = new Conjunction(p1, p2, p3);

		this.setClauses(p.getClauses());
	}
}