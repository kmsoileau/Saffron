/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 4, 2019
 */
package naturalnumbers.intervals;

import bits.Conjunction;
import bits.Disjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberAdder;

/**
 * 
 *
 */
public class NaturalNumberIntervalCongruenter extends Problem implements IProblem
{
	public NaturalNumberIntervalCongruenter(INaturalNumberInterval A, INaturalNumberInterval B) throws Exception
	{
		this(A, B, new NaturalNumber());
	}

	public NaturalNumberIntervalCongruenter(INaturalNumberInterval A, INaturalNumberInterval B, INaturalNumber X)
			throws Exception
	{
		IProblem p1 = new Conjunction(new NaturalNumberAdder(A.getLower(), X, B.getLower()),
				new NaturalNumberAdder(A.getUpper(), X, B.getUpper()));

		IProblem p2 = new Conjunction(new NaturalNumberAdder(B.getLower(), X, A.getLower()),
				new NaturalNumberAdder(B.getUpper(), X, A.getUpper()));

		IProblem p = new Disjunction(p1, p2);

		// IProblem p = new Disjunction(new Conjunction(new NaturalNumberAdder(
		// A.getLower(), X, B.getLower()), new NaturalNumberAdder(
		// A.getUpper(), X, B.getUpper())), new Conjunction(
		// new NaturalNumberAdder(B.getLower(), X, A.getLower()),
		// new NaturalNumberAdder(B.getUpper(), X, A.getUpper())));

		this.setClauses(p.getClauses());
	}
}
