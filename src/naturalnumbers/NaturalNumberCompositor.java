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
public class NaturalNumberCompositor extends Problem implements IProblem
{
	public NaturalNumberCompositor(INaturalNumber Z) throws Exception
	{
		INaturalNumber A = new NaturalNumber();
		INaturalNumber B = new NaturalNumber();
		INaturalNumber X = new NaturalNumber();
		INaturalNumber Y = new NaturalNumber();
		INaturalNumber Two = new NaturalNumber();

		NaturalNumberFixer nnf2 = new NaturalNumberFixer(Two, 2);
		NaturalNumberAdder nnax = new NaturalNumberAdder(A, Two, X);
		NaturalNumberAdder nnby = new NaturalNumberAdder(B, Two, Y);
		NaturalNumberMultiplier nnm = new NaturalNumberMultiplier(X, Y, Z);

		IProblem p = new Conjunction(new IProblem[]
		{ nnf2, nnax, nnby, nnm });

		this.setClauses(p.getClauses());
	}
}
