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

public class NaturalNumberTriangular extends Problem implements IProblem
{
	public NaturalNumberTriangular(INaturalNumber Z) throws Exception
	{
		INaturalNumber X = new NaturalNumber();
		INaturalNumber Two = new NaturalNumber();
		INaturalNumber TwoZ = new NaturalNumber();
		INaturalNumber Y = new NaturalNumber();

		IProblem p1 = new NaturalNumberFixer(Two, 2);
		IProblem p2 = new NaturalNumberIncrementer(X, Y);
		IProblem p3 = new NaturalNumberMultiplier(Two, Z, TwoZ);
		IProblem p4 = new NaturalNumberMultiplier(X, Y, TwoZ);

		IProblem p = new Conjunction(new IProblem[]
		{ p1, p2, p3, p4 });

		this.setClauses(p.getClauses());
	}

	public NaturalNumberTriangular(INaturalNumber X, INaturalNumber Two,
			INaturalNumber TwoZ, INaturalNumber Y, INaturalNumber Z)
			throws Exception
	{
		IProblem p1 = new NaturalNumberFixer(Two, 2);
		IProblem p2 = new NaturalNumberIncrementer(X, Y);
		IProblem p3 = new NaturalNumberMultiplier(Two, Z, TwoZ);
		IProblem p4 = new NaturalNumberMultiplier(X, Y, TwoZ);

		IProblem p = new Conjunction(new IProblem[]
		{ p1, p2, p3, p4 });

		this.setClauses(p.getClauses());
	}
}