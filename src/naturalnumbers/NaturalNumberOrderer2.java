package naturalnumbers;

import bits.BitEqualizer;
import bits.BitFixer;
import bits.Conjunction;
import bits.Disjunction;
import bits.EnhancedProblem;
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
 * @since Mar 4, 2019
 */
public class NaturalNumberOrderer2 extends Problem implements IProblem
{
	private static int depthCount = 0;

	public NaturalNumberOrderer2(INaturalNumber X, INaturalNumber Y) throws Exception
	{
		depthCount++;
		IProblem p0011 = null;
		IProblem p01 = null;
		IProblem p10 = null;

		if (depthCount == NaturalNumber.getLength())
		{
			p0011 = new Conjunction(new BitEqualizer(X.getBooleanVariable(NaturalNumber.getLength() - 1),
					Y.getBooleanVariable(NaturalNumber.getLength() - 1)));
			p01 = new Conjunction(new BitFixer(X.getBooleanVariable(NaturalNumber.getLength() - 1), false),
					new BitFixer(Y.getBooleanVariable(NaturalNumber.getLength() - 1), true));
			p10 = new Conjunction(new BitFixer(X.getBooleanVariable(NaturalNumber.getLength() - 1), true),
					new BitFixer(Y.getBooleanVariable(NaturalNumber.getLength() - 1), false),
					EnhancedProblem.unsolvableProblem());
		}
		else
		{
			INaturalNumber Xr = new NaturalNumber();
			INaturalNumber Yr = new NaturalNumber();
			IProblem leftX = new NaturalNumberLeftShifter(X, Xr);
			IProblem leftY = new NaturalNumberLeftShifter(Y, Yr);
			IProblem recurse = new Conjunction(new NaturalNumberOrderer2(Xr, Yr), leftX, leftY);
			p0011 = new Conjunction(new BitEqualizer(X.getBooleanVariable(NaturalNumber.getLength() - 1),
					Y.getBooleanVariable(NaturalNumber.getLength() - 1)), recurse);
			p01 = new Conjunction(new BitFixer(X.getBooleanVariable(NaturalNumber.getLength() - 1), false),
					new BitFixer(Y.getBooleanVariable(NaturalNumber.getLength() - 1), true));
			p10 = new Conjunction(new BitFixer(X.getBooleanVariable(NaturalNumber.getLength() - 1), true),
					new BitFixer(Y.getBooleanVariable(NaturalNumber.getLength() - 1), false),
					EnhancedProblem.unsolvableProblem());
		}
		this.setClauses(new Disjunction(p0011, p01, p10).getClauses());
	}
}
