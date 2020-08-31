package naturalnumbers;

import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 3, 2005
 */
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
