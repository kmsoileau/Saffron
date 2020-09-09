package naturalnumbers.intervals;

import bits.Conjunction;
import bits.Disjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.NaturalNumberOrderer;
import naturalnumbers.intervals.exceptions.NaturalNumberIntervalIntersectorException;

/**
 * An extension of the Problem class which imposes a relation on two
 * INaturalNumberIntervals. For example, the Problem instance p defined by
 *
 * <p>
 * <code>IProblem p=new NaturalNumberIntervalIntersector(A, B);</code>
 * </p>
 *
 * is satisfied if and only if the intervals A and B have at least one member in
 * common.
 * <p>
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2018/11/27
 */
public class NaturalNumberIntervalIntersector extends Problem implements IProblem
{
	public NaturalNumberIntervalIntersector(INaturalNumber A, INaturalNumber B, INaturalNumber C, INaturalNumber D)
			throws Exception
	{
		if (A == null || B == null || C == null || D == null)
			throw new NaturalNumberIntervalIntersectorException("Null INaturalNumber passed to constructor.");

		this.setClauses(
				new Conjunction(new NaturalNumberOrderer(A, B), new NaturalNumberOrderer(C, D),
						new Disjunction(new Conjunction(new NaturalNumberOrderer(A, C), new NaturalNumberOrderer(C, B)),
								new Conjunction(new NaturalNumberOrderer(C, A), new NaturalNumberOrderer(A, D))))
										.getClauses());
	}

	public NaturalNumberIntervalIntersector(INaturalNumberInterval theOne, INaturalNumberInterval theOther)
			throws Exception
	{
		this(theOne.getLower(), theOne.getUpper(), theOther.getLower(), theOther.getUpper());
	}
}