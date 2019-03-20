package in_development;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bits.Conjunction;
import bits.Disjunction;
import bits.IProblem;
import bits.Problem;
import exceptions.bits.ExclusiveDisjunctionException;

/**
 * An extension of the Problem class which expresses the exclusive disjunction
 * of two given IProblems. More specifically, the IProblem p defined by
 *
 * <p>
 * <code>IProblem p=new ExclusiveDisjunction(first,second);</code>
 * </p>
 *
 * is satisfied by an ICertificate c if and only if the IProblem
 * <code>first</code> is satisfied by <code>c</code> and the IProblem
 * <code>second</code> is not satisfied by <code>c</code> or the IProblem
 * <code>first</code> is not satisfied by <code>c</code> and the IProblem
 * <code>second</code> is satisfied by <code>c</code>.
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.01
 * @since 2007/08/22
 */
public class ExclusiveDisjunction extends Problem implements IProblem
{
	public ExclusiveDisjunction(IProblem problem) throws Exception
	{
		this.setClauses(problem.getClauses());
	}

	public ExclusiveDisjunction(IProblem first, IProblem second)
			throws Exception
	{
		if (first == null || second == null)
			throw new ExclusiveDisjunctionException(
					"Null IProblem was passed to constructor.");
		else
			this.setClauses(new Disjunction(new ProblemDifferencer(first,
					second), new ProblemDifferencer(second, first))
					.getClauses());
	}

	public ExclusiveDisjunction(IProblem first, IProblem second, IProblem third)
			throws Exception
	{
		this(new IProblem[]
		{ first, second, third });
	}

	public ExclusiveDisjunction(IProblem[] array) throws Exception
	{
		this(Arrays.asList(array));
	}

	public ExclusiveDisjunction(List<IProblem> list) throws Exception
	{
		if (list == null || list.size() == 0)
			throw new ExclusiveDisjunctionException(
					"Null IProblem was passed to constructor.");
		if (list.size() == 1)
			this.setClauses(list.get(0).getClauses());
		else
		{
			IProblem problem1 = list.get(0);
			IProblem problem2 = new Problem(problem1);
			for (int i = 1; i < list.size(); i++)
				problem2 = new Conjunction(problem2, new ProblemDenier(
						list.get(i)));

			IProblem problem3 = new ProblemDenier(problem1);
			ArrayList<IProblem> list2 = new ArrayList<IProblem>();
			for (int i = 1; i < list.size(); i++)
				list2.add(list.get(i));
			problem3 = new Conjunction(problem3,
					new ExclusiveDisjunction(list2));

			IProblem problem = new Disjunction(problem2, problem3);
			this.setClauses(problem.getClauses());
		}
	}
}