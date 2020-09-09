package bits;

import java.util.ArrayList;

import bits.exceptions.ConjunctionException;

/**
 * An extension of the Problem class which creates a new IProblem object from a
 * collection of IProblem objects that will be satisfied if and only if all of
 * the original IProblems are satisfied. For example, when the IProblem instance
 * p defined by
 * 
 * <pre>
 * IProblem p = new Conjunction(new IProblem[]
 * { p_1, p_2, p_3 });
 * </pre>
 * 
 * whenever the following truth equation will be satisfied: <blockquote>
 * 
 * <pre>
 * p_1 is satisfied
 * and
 * p_2 is satisfied
 * and
 * p_3 is satisfied.
 * </pre>
 * 
 * </blockquote>
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.2
 * @since Apr 8, 2005
 */
public class Conjunction extends Problem implements IProblem
{
	/**
	 * This and() method scrubs out null IClauses and duplicate IClauses within an
	 * IProblem. The other and() methods ultimately call this one, and thus all of
	 * the and() methods perform this scrubbing function.
	 * 
	 * @throws Exception
	 */
	private static IProblem and(IProblem[] p) throws Exception
	{
		if (p == null)
			throw new ConjunctionException("Null IProblem array passed to and method.");
		IProblem res = null;
		for (int i = 0; i < p.length; i++)
			if (p[i] != null)
			{
				if (res == null)
					res = Problem.newProblem();
				for (int j = 0; j < p[i].numberOfClauses(); j++)
				{
					IClause c = p[i].getClause(j);
					if (c != null && !res.contains(c))
						res.addClauseVoid(c);
				}
			}
		return res;
	}

	public Conjunction(ArrayList<IProblem> problemList) throws Exception
	{
		this(problemList.toArray(new IProblem[0]));
	}

	public Conjunction(IProblem problem) throws Exception
	{
		super.setClauses(problem.getClauses());
	}

	public Conjunction(IProblem p1, IProblem p2) throws Exception
	{
		this(new IProblem[]
		{ p1, p2 });
	}

	public Conjunction(IProblem p1, IProblem p2, IProblem p3) throws Exception
	{
		this(new IProblem[]
		{ p1, p2, p3 });
	}

	public Conjunction(IProblem p1, IProblem p2, IProblem p3, IProblem p4) throws Exception
	{
		this(new IProblem[]
		{ p1, p2, p3, p4 });
	}

	public Conjunction(IProblem p1, IProblem p2, IProblem p3, IProblem p4, IProblem p5) throws Exception
	{
		this(new IProblem[]
		{ p1, p2, p3, p4, p5 });
	}

	public Conjunction(IProblem[] group) throws Exception
	{
		IProblem p = Conjunction.and(group);
		if (p != null)
			this.setClauses(p.getClauses());
	}

	public Conjunction(IProblem[][] p) throws Exception
	{
		ArrayList<IProblem> q = new ArrayList<IProblem>();
		for (IProblem[] curr : p)
		{
			q.add(new Conjunction(curr));
		}

		this.setClauses(new Conjunction(q).getClauses());
	}

	/*
	 * public Conjunction(List<IProblem> problemList) throws Exception { IProblem[]
	 * o = (problemList.toArray(new IProblem[0])); IProblem[] p = new
	 * IProblem[o.length]; for (int i = 0; i < o.length; i++) { IProblem ip = o[i];
	 * p[i] = ip; } this.setClauses(new Conjunction(p).getClauses()); }
	 */
}