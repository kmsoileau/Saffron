package bits;

import bits.exceptions.ProblemDenierException;

/**
 * An extension of the Problem class which expresses the denial of a given
 * IProblem. More specifically, the IProblem p defined by
 *
 * <p>
 * <code>IProblem p=new ProblemDenier(problem);</code>
 * </p>
 *
 * is satisfied by an ICertificate c if and only if the IProblem problem is not
 * satisfied by c. It should be noted that this does not say anything conclusive
 * about the satisfiability of problem, it is useful mainly in constraining an
 * ICertificate away from those ICertificates which satisfy problem, if any.
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.01
 * @since 2005/12/26
 */
public class ProblemDenier extends Problem implements IProblem
{
	public ProblemDenier(IProblem problem) throws Exception
	{
		if (problem == null)
			throw new ProblemDenierException("Null IProblem passed to constructor.");
		if (problem.numberOfClauses() == 0)
			throw new ProblemDenierException("IProblem of zero size passed to constructor.");

		IClause qq = problem.getClause(0);
		IProblem res = new ClauseDenier(qq);
		for (int i = 1; i < problem.numberOfClauses(); i++)
		{
			IClause cls = problem.getClause(i);
			IProblem ip = new ClauseDenier(cls);
			res = new Disjunction(res, ip);
		}
		this.setClauses(res.getClauses());
	}
}

//package in_development;
//
//import java.util.ArrayList;
//
//import bits.Clause;
//import bits.ClauseDenier;
//import bits.Conjunction;
//import bits.Disjunction;
//import bits.IBooleanLiteral;
//import bits.IBooleanVariable;
//import bits.IClause;
//import bits.IProblem;
//import bits.Problem;
//import bits.exceptions.ProblemDenierException;
//
//public class ProblemDenier extends Problem implements IProblem
//{
//	private static IProblem denial(IProblem problem) throws Exception
//	{
//		int size = problem.size();
//		if (size == 0)
//		{
//			System.out.println("\ndenial: This problem is empty: " + problem);
//			return Problem.trivialProblem();
//		}
//
//		System.out.println("\ndenial: This problem is not empty: " + problem);
//		IBooleanVariable util = selectUtilityVariable(problem);
//		System.out
//				.println("\ndenial: Attemptin to find a split utility variable.");
//		if (util == null)
//		{
//			System.out
//					.println("\ndenial: No utility variables, so no split possible.");
//			IProblem[] r = new IProblem[size];
//			for (int i = 0; i < size; i++)
//			{
//				r[i] = new ClauseDenier(problem.getClause(i));
//			}
//			IProblem ret = new Disjunction(r);
//			System.out.println("\ndenial: returning " + ret);
//			return ret;
//		}
//		else
//		{
//			System.out.println("\ndenial: Splitting on: " + util.getName());
//			ArrayList<IProblem> qq = split(problem, util);
//
//			IProblem absent = qq.get(0);
//			IProblem barred = qq.get(1);
//			IProblem unbarred = qq.get(2);
//
//			System.out.print("\nabsent: " + absent);
//			System.out.print("\nbarred: " + barred);
//			System.out.print("\nunbarred: " + unbarred);
//
//			IProblem denialOfAbsent = denial(absent);
//			IProblem denialOfBarred = denial(barred);
//			IProblem denialOfUnbarred = denial(unbarred);
//
//			System.out.print("\ndenial of absent: " + absent + "\n is"
//					+ denialOfAbsent);
//			System.out.print("\ndenial of barred: " + barred + "\n is"
//					+ denialOfBarred);
//			System.out.print("\ndenial of unbarred: " + unbarred + "\n is"
//					+ denialOfUnbarred);
//
//			boolean istrivialda = isTrivial(denialOfAbsent);
//			boolean istrivialdb = isTrivial(denialOfBarred);
//			boolean istrivialdu = isTrivial(denialOfUnbarred);
//
//			if (istrivialda)
//			{
//				if (!istrivialdb && !istrivialdu)
//				{
//					return new Conjunction(denialOfBarred, denialOfUnbarred);
//				}
//			}
//			else
//			{
//				if (!istrivialdb && !istrivialdu)
//				{
//					return new Disjunction(denialOfAbsent, new Conjunction(
//							denialOfBarred, denialOfUnbarred));
//				}
//			}
//
//			return Problem.unsolvableProblem();
//		}
//	}
//
//	private static boolean isTrivial(IProblem problem) throws Exception
//	{
//		return ProblemDenier.scrubTrivia(problem).size() == 0;
//	}
//
//	private static IProblem scrubTrivia(IProblem problem) throws Exception
//	{
//		IProblem problemx = Problem.newProblem();
//		for (int i = 0; i < problem.size(); i++)
//		{
//			Clause curr = (Clause) problem.getClause(i);
//			if (!curr.isTrivial())
//			{
//				problemx.addClause(curr);
//			}
//		}
//		return problemx;
//	}
//
//	private static IBooleanVariable selectUtilityVariable(IProblem problem)
//			throws Exception
//	{
//		ArrayList<IBooleanVariable> bvs = problem.getBooleanVariables();
//		IBooleanVariable util = null;
//		for (IBooleanVariable curr : bvs)
//		{
//			if (curr.isUtility())
//			{
//				util = curr;
//				break;
//			}
//		}
//		return util;
//	}
//
//	private static ArrayList<IProblem> split(IProblem problem,
//			IBooleanVariable util) throws Exception
//	{
//		ArrayList<IProblem> ret = new ArrayList<IProblem>();
//		ArrayList<IClause> unbarred = new ArrayList<IClause>();
//		ArrayList<IClause> barred = new ArrayList<IClause>();
//		ArrayList<IClause> absent = new ArrayList<IClause>();
//
//		for (IClause c : problem)
//		{
//			if (((Clause) c).isTrivial())
//				continue;
//			IBooleanLiteral bl = c.getLiteral(util);
//			if (bl != null)
//			{
//				c.remove(bl);
//				if (bl.isBarred())
//					barred.add(c);
//				else
//					unbarred.add(c);
//			}
//			else
//			{
//				absent.add(c);
//			}
//		}
//
//		ret.add(new Problem(absent));
//		ret.add(new Problem(barred));
//		ret.add(new Problem(unbarred));
//
//		return ret;
//	}
//
//	public ProblemDenier(IProblem problem) throws Exception
//	{
//		if (problem == null)
//			throw new ProblemDenierException(
//					"Null IProblem passed to constructor.");
//		if (problem.size() == 0)
//			throw new ProblemDenierException(
//					"Empty IProblem passed to constructor.");
//
//		IProblem ret = null;
//		if (isTrivial(problem))
//		{
//			ret = Problem.unsolvableProblem();
//			System.out.println("This problem is trivial: " + ret);
//		}
//		else
//		{
//			ret = denial(problem);
//			System.out.println("This problem is not trivial: " + ret);
//		}
//		this.setClauses(ret.getClauses());
//	}
//}
