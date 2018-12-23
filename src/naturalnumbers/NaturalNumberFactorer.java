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

public class NaturalNumberFactorer extends Problem implements IProblem
{
	public NaturalNumberFactorer(INaturalNumber X, INaturalNumber Y,
			INaturalNumber Z) throws Exception
	{
		INaturalNumber A = new NaturalNumber("A");
		INaturalNumber B = new NaturalNumber("B");
		INaturalNumber Two = new NaturalNumber("Integer2");

		NaturalNumberFixer nnf2 = new NaturalNumberFixer(Two, 2);
		NaturalNumberAdder nnax = new NaturalNumberAdder(A, Two, X);
		NaturalNumberAdder nnby = new NaturalNumberAdder(B, Two, Y);
		NaturalNumberMultiplier nnm = new NaturalNumberMultiplier(X, Y, Z);

		IProblem p = new Conjunction(new IProblem[]
		{ nnf2, nnax, nnby, nnm });

		this.setClauses(p.getClauses());
	}
}