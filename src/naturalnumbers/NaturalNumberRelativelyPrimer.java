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

public class NaturalNumberRelativelyPrimer extends Problem implements IProblem
{
	public NaturalNumberRelativelyPrimer(INaturalNumber M, INaturalNumber N)
			throws Exception
	{
		this(M, N, new NaturalNumber(), new NaturalNumber());
	}

	public NaturalNumberRelativelyPrimer(INaturalNumber M, INaturalNumber N,
			INaturalNumber P, INaturalNumber Q) throws Exception
	{
		INaturalNumber A = new NaturalNumber();
		INaturalNumber B = new NaturalNumber();
		INaturalNumber One = new NaturalNumber();
		IProblem problem = new Conjunction(new IProblem[]
		{ new NaturalNumberFixer(One, 1), new NaturalNumberMultiplier(M, A, P),
				new NaturalNumberMultiplier(N, B, Q),
				new NaturalNumberAdder(Q, One, P) });

		this.setClauses(problem.getClauses());
	}
}