/*
 * BitStringListIntersector.java	1.0 05/10/20
 *
 * Copyright 2005 Positronic Software.
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

public class BitStringListIntersector extends Problem implements IProblem
{
	public BitStringListIntersector(IBitStringList T, IBitStringList A,
			IBitStringList B) throws Exception
	{
		if (T == null || A == null || B == null)
			throw new BitStringListException(
					"Passed null IBitStringList to BitStringListIntersecter constructor.");

		IProblem problem = null;
		for (int i = 0; i < A.size(); i++)
		{
			IBitString curr = A.getBitString(i);
			IProblem inT = new BitStringListMembershipper(curr, T);
			IProblem notInT = new BitStringListNonmembershipper(curr, T);
			IProblem inB = new BitStringListMembershipper(curr, B);
			IProblem notInB = new BitStringListNonmembershipper(curr, B);

			problem = new Conjunction(problem, new Disjunction(notInT, inB),
					new Disjunction(inT, notInB));
		}
		for (int i = 0; i < B.size(); i++)
		{
			IBitString curr = B.getBitString(i);
			IProblem inT = new BitStringListMembershipper(curr, T);
			IProblem notInT = new BitStringListNonmembershipper(curr, T);
			IProblem inA = new BitStringListMembershipper(curr, A);
			IProblem notInA = new BitStringListNonmembershipper(curr, A);

			problem = new Conjunction(problem, new Disjunction(notInT, inA),
					new Disjunction(inT, notInA));
		}
		for (int i = 0; i < T.size(); i++)
		{
			IBitString curr = T.getBitString(i);
			IProblem inA = new BitStringListMembershipper(curr, A);
			IProblem inB = new BitStringListMembershipper(curr, B);
			problem = new Conjunction(problem, inA, inB);
		}
		this.setClauses(problem.getClauses());
	}
	/*
	 * public BitStringListIntersector(IBitStringList T,IBitStringList
	 * A,IBitStringList B) throws Exception { if(T==null || A==null || B==null)
	 * throw new BitStringListException(
	 * "Passed null IBitStringList to BitStringListIntersecter constructor.");
	 * 
	 * IProblem problem=null; for(int i=0;i<A.size();i++) { IBitString
	 * curr=A.getBitString(i); IProblem inT=new
	 * BitStringListMembershipper(curr,T); IProblem notInT=new
	 * BitStringListNonmembershipper(curr,T); IProblem inA=new
	 * BitStringListMembershipper(curr,A); IProblem notInA=new
	 * BitStringListNonmembershipper(curr,A); IProblem inB=new
	 * BitStringListMembershipper(curr,B); IProblem notInB=new
	 * BitStringListNonmembershipper(curr,B);
	 * 
	 * problem=new Conjunction( new IProblem[] { problem, new
	 * Disjunction(notInT,new Conjunction(inA,inB)), new Disjunction(inT,new
	 * Disjunction(notInA,notInB)) }); } for(int i=0;i<B.size();i++) {
	 * IBitString curr=B.getBitString(i); IProblem inT=new
	 * BitStringListMembershipper(curr,T); IProblem notInT=new
	 * BitStringListNonmembershipper(curr,T); IProblem inA=new
	 * BitStringListMembershipper(curr,A); IProblem notInA=new
	 * BitStringListNonmembershipper(curr,A); IProblem inB=new
	 * BitStringListMembershipper(curr,B); IProblem notInB=new
	 * BitStringListNonmembershipper(curr,B);
	 * 
	 * problem=new Conjunction( new IProblem[] { problem, new
	 * Disjunction(notInT,new Conjunction(inA,inB)), new Disjunction(inT,new
	 * Disjunction(notInA,notInB)) }); } for(int i=0;i<T.size();i++) {
	 * IBitString curr=T.getBitString(i); IProblem inT=new
	 * BitStringListMembershipper(curr,T); IProblem notInT=new
	 * BitStringListNonmembershipper(curr,T); IProblem inA=new
	 * BitStringListMembershipper(curr,A); IProblem notInA=new
	 * BitStringListNonmembershipper(curr,A); IProblem inB=new
	 * BitStringListMembershipper(curr,B); IProblem notInB=new
	 * BitStringListNonmembershipper(curr,B);
	 * 
	 * problem=new Conjunction( new IProblem[] { problem, new
	 * Disjunction(notInT,new Conjunction(inA,inB)), new Disjunction(inT,new
	 * Disjunction(notInA,notInB)) }); } this.setClauses(problem.getClauses());
	 * }
	 */
	/*
	 * public BitStringListIntersector(IBitStringList T,IBitStringList
	 * A,IBitStringList B) throws Exception { if(T==null || A==null || B==null)
	 * throw new BitStringListException(
	 * "Passed null IBitStringList to BitStringListIntersecter constructor.");
	 * 
	 * IProblem problem=null; for(int i=0;i<A.size();i++) { IBitString
	 * curr=A.getBitString(i); IProblem notInT=new
	 * BitStringListNonmembershipper(curr,T); IProblem inA=new
	 * BitStringListMembershipper(curr,A); IProblem inB=new
	 * BitStringListMembershipper(curr,B); IProblem notInA=new
	 * BitStringListNonmembershipper(curr,A); IProblem notInB=new
	 * BitStringListNonmembershipper(curr,B); IProblem inT=new
	 * BitStringListMembershipper(curr,T); problem=new Conjunction( new
	 * IProblem[] { problem, new Disjunction(notInT,inA), new
	 * Disjunction(notInT,inB), new Disjunction(notInA,inT), new
	 * Disjunction(notInB,inT) }); } for(int i=0;i<B.size();i++) { IBitString
	 * curr=B.getBitString(i); IProblem notInT=new
	 * BitStringListNonmembershipper(curr,T); IProblem inA=new
	 * BitStringListMembershipper(curr,A); IProblem inB=new
	 * BitStringListMembershipper(curr,B); IProblem notInA=new
	 * BitStringListNonmembershipper(curr,A); IProblem notInB=new
	 * BitStringListNonmembershipper(curr,B); IProblem inT=new
	 * BitStringListMembershipper(curr,T); problem=new Conjunction( new
	 * IProblem[] { problem, new Disjunction(notInT,inA), new
	 * Disjunction(notInT,inB), new Disjunction(notInA,inT), new
	 * Disjunction(notInB,inT) }); } this.setClauses(problem.getClauses()); }
	 */
}
