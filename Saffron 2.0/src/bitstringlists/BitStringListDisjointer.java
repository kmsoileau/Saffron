/*
 * BitStringListDisjointer.java	1.0 05/12/09
 *
 * Copyright 2005 Positronic Software.
 *
 *
 */

package bitstringlists;

import bits.Conjunction;
import bits.IProblem;
import bits.Problem;
import bitstringlists.exceptions.BitStringListDisjointerException;

/**
 * This IProblem imposes the constraint that IBitStrings A and B are disjoint,
 * i.e. they have no common element.
 * 
 * @author ksoileau
 */
public class BitStringListDisjointer extends Problem implements IProblem
{
	public BitStringListDisjointer(IBitStringList A, IBitStringList B)
			throws Exception
	{
		if (A == null)
			throw new BitStringListDisjointerException(
					"Passed a null IBitStringList to constructor.");
		if (B == null)
			throw new BitStringListDisjointerException(
					"Passed a null IBitStringList to constructor.");
		if (A.size() == 0 || B.size() == 0)
			this.setClauses(Problem.trivialProblem().getClauses());
		else
		{
			IProblem res[] = new IProblem[A.size() + B.size()];
			for (int i = 0; i < A.size(); i++)
				res[i] = new BitStringListNonmembershipper(A.getBitString(i), B);
			for (int i = A.size(); i < A.size() + B.size(); i++)
				res[i] = new BitStringListNonmembershipper(B.getBitString(i
						- A.size()), A);
			this.setClauses(new Conjunction(res).getClauses());
		}
	}

	/*
	 * public BitStringListDisjointer(IBitStringList A, IBitStringList B) throws
	 * Exception { if(A==null) throw new BitStringListDisjointerException(
	 * "Passed a null IBitStringList to constructor."); if(B==null) throw new
	 * BitStringListDisjointerException
	 * ("Passed a null IBitStringList to constructor."); if(A.size()==0 ||
	 * B.size()==0) this.setClauses(Problem.trivialProblem().getClauses()); else
	 * { IProblem res[]=new IProblem[A.size()+B.size()]; for(int
	 * i=0;i<A.size();i++) res[i]=new
	 * BitStringListNonmembershipper(A.getBitString(i),B); this.setClauses(new
	 * Conjunction(res).getClauses()); for(int i=0;i<B.size();i++) res[i]=new
	 * BitStringListNonmembershipper(B.getBitString(i),A); this.setClauses(new
	 * Conjunction(res).getClauses()); } }
	 */
}