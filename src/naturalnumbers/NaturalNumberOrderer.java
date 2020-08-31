package naturalnumbers;

import bits.BitFixer;
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
* @since Mar 4, 2019
*/
public class NaturalNumberOrderer extends Problem implements IProblem
{
	private static int nNOCount;

	public NaturalNumberOrderer(INaturalNumber X, INaturalNumber Y)
			throws Exception
	{
		// X<=Y
		// There exists Z such that Y=X+Z
		INaturalNumber C = new NaturalNumber("NaturalNumberOrderer-"
				+ nNOCount++);
		IProblem p1 = new NaturalNumberAdder(X, new NaturalNumber(
				"NaturalNumberOrderer-" + nNOCount++), Y, C);
		// Constrain overflow bit
		IProblem p2 = new BitFixer(C.getBooleanVariable(NaturalNumber
				.getLength() - 1), false);
		this.setClauses(new Conjunction(p1, p2).getClauses());
	}

	public NaturalNumberOrderer(INaturalNumber X, long y) throws Exception
	{
		INaturalNumber dummy = new NaturalNumber("NaturalNumberOrderer-"
				+ nNOCount++);
		IProblem p1 = new NaturalNumberFixer(dummy, y);
		IProblem p2 = new NaturalNumberOrderer(X, dummy);
		this.setClauses(new Conjunction(p1, p2).getClauses());
	}

	public NaturalNumberOrderer(long x, INaturalNumber Y) throws Exception
	{
		INaturalNumber dummy = new NaturalNumber("NaturalNumberOrderer-"
				+ nNOCount++);
		IProblem p1 = new NaturalNumberFixer(dummy, x);
		IProblem p2 = new NaturalNumberOrderer(dummy, Y);
		this.setClauses(new Conjunction(p1, p2).getClauses());
	}
}
