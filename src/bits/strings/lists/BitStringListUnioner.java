/*
 * BitStringListUnioner.java	1.0 06/02/09
 *
 * Copyright 2006 Positronic Software.
 *
 *
 */

package bits.strings.lists;

import bits.Conjunction;
import bits.Disjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import bits.strings.lists.exceptions.BitStringListException;

public class BitStringListUnioner extends Problem implements IProblem
{
	public BitStringListUnioner(IBitStringList union, IBitStringList A,
			IBitStringList B) throws Exception
	{
		if (union == null || A == null || B == null)
			throw new BitStringListException(
					"Passed null IBitStringList to BitStringListAnder constructor.");

		IProblem problem = null;
		for (int i = 0; i < A.size(); i++)
		{
			IProblem inT = new BitStringListMembershipper(A.getBitString(i),
					union);
			problem = new Conjunction(problem, inT);
		}
		for (int i = 0; i < B.size(); i++)
		{
			IProblem inT = new BitStringListMembershipper(B.getBitString(i),
					union);
			problem = new Conjunction(problem, inT);
		}
		for (int i = 0; i < union.size(); i++)
		{
			IBitString curr = union.getBitString(i);
			IProblem inA = new BitStringListMembershipper(curr, A);
			IProblem inB = new BitStringListMembershipper(curr, B);
			problem = new Conjunction(problem, new Disjunction(inA, inB));
		}
		this.setClauses(problem.getClauses());
	}

	/*
	 * public BitStringListUnioner(IBitStringList T,IBitStringList
	 * A,IBitStringList B) throws Exception { if(T==null || A==null || B==null)
	 * throw new BitStringListException(
	 * "Passed null IBitStringList to BitStringListAnder constructor.");
	 * 
	 * IProblem problem=null; for(int i=0;i<A.size();i++) { IBitString
	 * curr=A.getBitString(i); IProblem notInT=new
	 * BitStringListNonmembershipper(curr,T); IProblem inA=new
	 * BitStringListMembershipper(curr,A); IProblem inB=new
	 * BitStringListMembershipper(curr,B); IProblem notInA=new
	 * BitStringListNonmembershipper(curr,A); IProblem notInB=new
	 * BitStringListNonmembershipper(curr,B); IProblem inT=new
	 * BitStringListMembershipper(curr,T); problem=new Conjunction(problem, new
	 * Disjunction(notInT,inA,inB), new Disjunction(new
	 * Conjunction(notInA,notInB),inT)); } for(int i=0;i<B.size();i++) {
	 * IBitString curr=B.getBitString(i); IProblem p1=new
	 * BitStringListNonmembershipper(curr,T); IProblem p2=new
	 * BitStringListMembershipper(curr,A); IProblem p3=new
	 * BitStringListMembershipper(curr,B); IProblem p4=new
	 * BitStringListNonmembershipper(curr,A); IProblem p5=new
	 * BitStringListNonmembershipper(curr,B); IProblem p6=new
	 * BitStringListMembershipper(curr,T); problem=new Conjunction(problem, new
	 * Disjunction(p1,p2,p3), new Disjunction(new Conjunction(p4,p5),p6)); }
	 * this.setClauses(problem.getClauses()); }
	 */
}