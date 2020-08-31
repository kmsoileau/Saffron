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
* @since Mar 4, 2019
*/
public class NaturalNumberStrictOrderer extends Problem implements IProblem
{
	private static int nNSOCount;

	public NaturalNumberStrictOrderer(INaturalNumber X, INaturalNumber Y)
			throws Exception
	{
		// X<Y
		// There exists Z such that Y=X+1+Z
		INaturalNumber XX = new NaturalNumber("NaturalNumberStrictOrderer-"
				+ nNSOCount++);
		IProblem p1 = new NaturalNumberIncrementer(X, XX);
		IProblem p2 = new NaturalNumberOrderer(XX, Y);
		this.setClauses(new Conjunction(p1, p2).getClauses());
	}

	public NaturalNumberStrictOrderer(INaturalNumber X, long y)
			throws Exception
	{
		INaturalNumber dummy = new NaturalNumber("NaturalNumberStrictOrderer-"
				+ nNSOCount++);
		IProblem p1 = new NaturalNumberFixer(dummy, y);
		IProblem p2 = new NaturalNumberStrictOrderer(X, dummy);
		this.setClauses(new Conjunction(p1, p2).getClauses());
	}

	public NaturalNumberStrictOrderer(long x, INaturalNumber Y)
			throws Exception
	{
		INaturalNumber dummy = new NaturalNumber("NaturalNumberStrictOrderer-"
				+ nNSOCount++);
		IProblem p1 = new NaturalNumberFixer(dummy, x);
		IProblem p2 = new NaturalNumberStrictOrderer(dummy, Y);
		this.setClauses(new Conjunction(p1, p2).getClauses());
	}
}
