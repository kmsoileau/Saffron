/*
 * BitStringListDifferencer.java	1.0 07/01/17
 *
 * Copyright 2006 Positronic Software.
 *
 *
 */

package bitstringlists;

import bits.Conjunction;
import bits.Disjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import bitstringlists.exceptions.BitStringListDisjointerException;

/**
 * This IProblem imposes the constraint that IBitString T is the difference
 * between IBitStrings A and B, i.e. that T = A $ B.
 * 
 * @author ksoileau
 */
public class BitStringListDifferencer extends Problem implements IProblem
{
	public BitStringListDifferencer(IBitStringList A, IBitStringList B,
			IBitStringList aMinusB) throws Exception
	{
		if (A == null)
			throw new BitStringListDisjointerException(
					"Passed a null IBitStringList to constructor.");
		if (B == null)
			throw new BitStringListDisjointerException(
					"Passed a null IBitStringList to constructor.");
		if (aMinusB == null)
			throw new BitStringListDisjointerException(
					"Passed a null IBitStringList to constructor.");

		// T=A\B <=> (B & T = null) and (B | T = A | B)
		// in T: notInB and inA
		// in A: (inB or inT) and (notInB or notInT)
		// in B: notInT and (inA or inB)
		IProblem problem = null;
		for (int i = 0; i < aMinusB.size(); i++)
		{
			IBitString curr = aMinusB.getBitString(i);
			problem = new Conjunction(problem,
					new BitStringListNonmembershipper(curr, B),
					new BitStringListMembershipper(curr, A));
		}
		for (int i = 0; i < A.size(); i++)
		{
			IBitString curr = A.getBitString(i);
			problem = new Conjunction(problem, new Disjunction(
					new BitStringListNonmembershipper(curr, B),
					new BitStringListNonmembershipper(curr, aMinusB)),
					new Disjunction(new BitStringListMembershipper(curr, B),
							new BitStringListMembershipper(curr, aMinusB)));
		}
		for (int i = 0; i < B.size(); i++)
		{
			IBitString curr = B.getBitString(i);
			problem = new Conjunction(problem,
					new BitStringListNonmembershipper(curr, aMinusB),
					new Disjunction(new BitStringListMembershipper(curr, A),
							new BitStringListMembershipper(curr, B)));
		}
		this.setClauses(problem.getClauses());
	}
}