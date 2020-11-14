package bits;

import java.util.ArrayList;

import bits.exceptions.DisjunctionException;

/**
 * Returns an IProblem that is satisfied if and only at least one of the
 * IProblems passed to it as parameters is satisfied.
 * 
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.2
 * @since 2005/04/08
 */
public class Disjunction extends Problem implements IProblem
{
	/**
	 * This or() method takes two Problems <code>ptrue</code> and
	 * <code>pfalse</code> and creates a new Problem, let's call it <code>p</code>.
	 * If <code>b</code> is true, <code>p</code> is equivalent to the Problem
	 * <code>ptrue</code>. Likewise, if <code>b</code> is false, <code>p</code> is
	 * equivalent to the Problem <code>pfalse</code>.
	 * 
	 */
	private static IProblem or(IProblem ptrue, IProblem pfalse, IBooleanVariable b) throws Exception
	{
		if (pfalse == null)
			return Disjunction.or(new IProblem[]
			{ ptrue }, new IBooleanVariable[]
			{ b });
		if (ptrue == null)
			return Disjunction.or(new IProblem[]
			{ pfalse }, new IBooleanVariable[]
			{ b });

		if (pfalse.size() == 0)
			return Disjunction.or(new IProblem[]
			{ ptrue }, new IBooleanVariable[]
			{ b });

		if (ptrue.size() == 0)
			return Disjunction.or(new IProblem[]
			{ pfalse }, new IBooleanVariable[]
			{ b });

		IClause[] clfalse = pfalse.getClauses();
		ArrayList<IClause> clfalseNew = new ArrayList<IClause>();
		for (int i = 0; i < clfalse.length; i++)
		{
			Clause q = (Clause) ((Clause) clfalse[i]).clone();
			clfalseNew.add(q.or(b));
		}

		IClause[] cltrue = ptrue.getClauses();
		ArrayList<IClause> cltrueNew = new ArrayList<IClause>();
		for (int i = 0; i < cltrue.length; i++)
		{
			Clause q = (Clause) ((Clause) cltrue[i]).clone();
			cltrueNew.add(q.orNot(b));
		}

		return new Conjunction(new Problem(clfalseNew.toArray(new IClause[0])),
				new Problem(cltrueNew.toArray(new IClause[0])));
	}

	/**
	 *
	 * @return If problemArray={p_0,p_1,p_2,...,p_{n-1}} then returns an IProblem
	 *         satisfied if and only if at least one of the following IProblems is
	 *         satisfied:
	 * 
	 *         <pre>
	 * p_0
	 * p_1
	 * p_2
	 * .
	 * .
	 * .
	 * p_{n-1}
	 *         </pre>
	 * 
	 */
	private static IProblem or(IProblem[] problemArray) throws Exception
	{
		if (problemArray == null)
			throw new DisjunctionException("Null IProblem array passed to or method.");
		int n = problemArray.length;
		if (n == 0)
			throw new DisjunctionException("IProblem array of zero length passed to or method.");
		IProblem ret = null;
		;
		if (n == 1)
			ret = problemArray[0];
		if (n > 1)
		{
			IBooleanVariable[] b = new IBooleanVariable[n - 1];
			for (int i = 0; i < b.length; i++)
				b[i] = BooleanVariable.getBooleanVariable();
			ret = Disjunction.or(problemArray, b);
		}

		return ret;
	}

	/**
	 * If
	 * 
	 * <pre>
	 * p={p_0,p_1,p_2,...,p_{n-1}}
	 * and
	 * b={x_0,x_1,x_2,...,x_{n-2}}
	 * </pre>
	 * 
	 * then
	 * 
	 * @return {r_0,r_1,r_2,...,r_{n-2}} where
	 * 
	 *         <pre>
	 * r_0=Disjunction.or(p_0,r_1,x_0)
	 * r_1=Disjunction.or(p_1,r_2,x_1) 
	 * r_2=Disjunction.or(p_2,r_3,x_2) 
	 * .
	 * .
	 * .
	 * r_{n-3}=Disjunction.or(p_{n-3},r_{n-2},x_{n-3})
	 * r_{n-2}=Disjunction.or(p_{n-2},p_{n-1},x_{n-2})
	 *         </pre>
	 * 
	 *         In effect, for the first <code>i</code> such that <code>x_i</code> is
	 *         true, <code>p_i</code> is returned. If no such <code>i</code> exists,
	 *         <code>p_{n-1}</code> is returned.
	 * 
	 * @param problemArray         IProblem[]
	 * @param booleanVariableArray IBooleanVariable[]
	 * @throws Exception _
	 * 
	 */
	public static IProblem or(IProblem[] problemArray, IBooleanVariable[] booleanVariableArray) throws Exception
	{
		if (problemArray == null)
			throw new DisjunctionException("Null IProblem array passed to or method.");
		if (booleanVariableArray == null)
			throw new DisjunctionException("Null IBooleanVariable array passed to or method.");
		int numberOfProblems = problemArray.length;
		if (numberOfProblems == 0)
			throw new DisjunctionException("IProblem array of zero length passed to or method.");
		if (numberOfProblems == 1)
			return problemArray[0];
		if (numberOfProblems != booleanVariableArray.length + 1)
			throw new DisjunctionException("problemArray.length != booleanVariableArray.length + 1");
		if (numberOfProblems > 1)
		{
			IProblem[] r = new IProblem[numberOfProblems - 1];
			r[numberOfProblems - 2] = Disjunction.or(problemArray[numberOfProblems - 2],
					problemArray[numberOfProblems - 1], booleanVariableArray[numberOfProblems - 2]);
			for (int i = numberOfProblems - 3; i >= 0; i--)
				r[i] = Disjunction.or(problemArray[i], r[i + 1], booleanVariableArray[i]);
			return r[0];
		}
		return null;
	}

	/**
	 *
	 * Returns an IProblem equivalent to the logical OR of the IProblems in
	 * problemList.
	 * 
	 * @throws Exception An instance of Exception
	 * @param problemList IProblem ArrayList
	 */
	public Disjunction(ArrayList<IProblem> problemList) throws Exception
	{
		this(problemList.toArray(new IProblem[0]));
	}

	public Disjunction(IBooleanVariable b, IProblem ptrue, IProblem pfalse) throws Exception
	{
		IProblem p = Disjunction.or(ptrue, pfalse, b);
		if (p != null)
			this.setClauses(p.getClauses());
	}

	/**
	 * If
	 * 
	 * <pre>
	 * problemArray={p_0,p_1,p_2,...,p_{n-1}}
	 * and
	 * booleanVariableArray={x_0,x_1,x_2,...,x_{n-2}}
	 * </pre>
	 * 
	 * then
	 * 
	 * Returns an {r_0,r_1,r_2,...,r_{n-2}} where
	 * 
	 * <pre>
	 * r_0=Disjunction.or(p_0,r_1,x_0)
	 * r_1=Disjunction.or(p_1,r_2,x_1) 
	 * r_2=Disjunction.or(p_2,r_3,x_2) 
	 * .
	 * .
	 * .
	 * r_{n-3}=Disjunction.or(p_{n-3},r_{n-2},x_{n-3})
	 * r_{n-2}=Disjunction.or(p_{n-2},p_{n-1},x_{n-2})
	 * </pre>
	 * 
	 * In effect, for the first <code>i</code> such that <code>x_i</code> is true,
	 * <code>p_i</code> is returned. If no such <code>i</code> exists,
	 * <code>p_{n-1}</code> is returned.
	 * 
	 * @throws Exception An instance of Exception
	 * @param problemArray         IProblem[]
	 * @param booleanVariableArray IBooleanVariable[]
	 */
	public Disjunction(IBooleanVariable[] booleanVariableArray, IProblem[] problemArray) throws Exception
	{
		if (problemArray == null)
			throw new DisjunctionException("Null IProblem array passed to constructor.");
		if (problemArray.length == 0)
			throw new DisjunctionException("IProblem array of zero length passed to constructor.");
		if (booleanVariableArray == null)
			throw new DisjunctionException("Null IBooleanVariable array passed to constructor.");
		if (booleanVariableArray.length == 0)
			throw new DisjunctionException("IBooleanVariable array of zero length passed to constructor.");

		IProblem p = Disjunction.or(problemArray, booleanVariableArray);
		if (p != null)
			this.setClauses(p.getClauses());
	}

	/**
	 *
	 * Returns an IProblem equivalent to the IProblem p.
	 * 
	 * @throws Exception An instance of Exception
	 * @param p IProblem
	 */
	public Disjunction(IProblem p) throws Exception
	{
		this(new IProblem[]
		{ p });
	}

	/**
	 * Returns an IProblem satisfied if and only if at least one of the following
	 * IProblems is satisfied:
	 * 
	 * <pre>
	 * p_1
	 * p_2
	 * </pre>
	 * 
	 * @throws Exception An instance of Exception
	 * @param p1 IProblem
	 * @param p2 IProblem
	 */
	public Disjunction(IProblem p1, IProblem p2) throws Exception
	{
		this(new IProblem[]
		{ p1, p2 });
	}

	/**
	 *
	 * Returns an IProblem satisfied if and only if at least one of the following
	 * IProblems is satisfied:
	 * 
	 * <pre>
	 * p_1
	 * p_2
	 * p_3
	 * </pre>
	 * 
	 * @throws Exception An instance of Exception
	 * @param p1 IProblem
	 * @param p2 IProblem
	 * @param p3 IProblem
	 */
	public Disjunction(IProblem p1, IProblem p2, IProblem p3) throws Exception
	{
		this(new IProblem[]
		{ p1, p2, p3 });
	}

	/**
	 *
	 * Returns an IProblem satisfied if and only if at least one of the following
	 * IProblems is satisfied:
	 * 
	 * <pre>
	 * p_1
	 * p_2
	 * p_3
	 * p_4
	 * </pre>
	 * 
	 * @throws Exception An instance of Exception*
	 * @param p1 IProblem
	 * @param p2 IProblem
	 * @param p3 IProblem
	 * @param p4 IProblem
	 */
	public Disjunction(IProblem p1, IProblem p2, IProblem p3, IProblem p4) throws Exception
	{
		this(new IProblem[]
		{ p1, p2, p3, p4 });
	}

	/**
	 *
	 * Returns an IProblem satisfied if and only if at least one of the following
	 * IProblems is satisfied:
	 * 
	 * <pre>
	 * problemArray[0]
	 * problemArray[1]
	 * problemArray[2]
	 * .
	 * .
	 * .
	 * problemArray[n-1]
	 * 
	 * where n=problemArray.length.
	 * </pre>
	 * 
	 * @throws Exception An instance of Exception
	 * @param problemArray IProblem[]
	 */
	public Disjunction(IProblem[] problemArray) throws Exception
	{
		if (problemArray == null)
			throw new DisjunctionException("Null IProblem array passed to or method.");
		// if (problemArray.length == 0)
		// throw new DisjunctionException(
		// "IProblem array of zero length passed to or method.");
		if (problemArray.length == 0)
		{
			this.setClauses(unsolvableProblem().getClauses());
			return;
		}

		IProblem p = Disjunction.or(problemArray);
		if (p != null)
			this.setClauses(p.getClauses());
	}
}