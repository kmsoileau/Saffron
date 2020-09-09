/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 3, 2019
 */
package naturalnumbers;

import bits.BitFixer;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

/**
 * 
 *
 */
public class Disjunction extends Problem implements IProblem
{
	private static IProblem or(IProblem[] problemArray, INaturalNumber hitIndex) throws Exception
	{
		IBooleanVariable[] b = new IBooleanVariable[problemArray.length - 1];
		for (int i = 0; i < b.length; i++)
			b[i] = BooleanVariable.getBooleanVariable();

		IProblem[] p1 = new IProblem[b.length];
		for (int i = 0; i < b.length; i++)
		{
			p1[i] = new BitFixer(b[i], false);
		}
		IProblem q1 = new Conjunction(new Conjunction(p1), new NaturalNumberFixer(hitIndex, b.length));

		IProblem[] p2 = new IProblem[b.length];
		for (int i = 0; i < b.length; i++)
		{
			p2[i] = new Conjunction(new BitFixer(b[i], true), new NaturalNumberFixer(hitIndex, i));
		}
		IProblem q2 = new bits.Disjunction(p2);
		return new Conjunction(bits.Disjunction.or(problemArray, b), new bits.Disjunction(q1, q2));
	}

	public Disjunction(IProblem p1, IProblem p2) throws Exception
	{
		this(new IProblem[]
		{ p1, p2 });
	}

	public Disjunction(IProblem p1, IProblem p2, IProblem p3) throws Exception
	{
		this(new IProblem[]
		{ p1, p2, p3 });
	}

	public Disjunction(IProblem p1, IProblem p2, IProblem p3, IProblem p4) throws Exception
	{
		this(new IProblem[]
		{ p1, p2, p3, p4 });
	}

	public Disjunction(IProblem[] problemArray) throws Exception
	{
		this.setClauses(or(problemArray, new NaturalNumber()).getClauses());
	}

	public Disjunction(IProblem[] problemArray, INaturalNumber hitIndex) throws Exception
	{
		this.setClauses(or(problemArray, hitIndex).getClauses());
	}
}
