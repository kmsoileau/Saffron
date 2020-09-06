package bits.strings.lists;

import bits.Conjunction;
import bits.Disjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import bits.strings.BitStringEqualizer;
import bits.strings.lists.exceptions.BitStringListIncrementerException;

/**
 * This IProblem detects whether one IBitStringList, called smaller, is a subset
 * of another IBitStringList, called larger AND they differ by a single
 * IBitString, called singleton.
 * 
 * @author Kerry Michael Soileau
 *
 */
public class BitStringListIncrementer extends Problem implements IProblem
{
	public BitStringListIncrementer(IBitStringList smaller,
			IBitString singleton, IBitStringList larger) throws Exception
	{
		if (smaller == null || larger == null)
			throw new BitStringListIncrementerException(
					"Passed a null IBitStringList to a constructor.");
		if (singleton == null)
			throw new BitStringListIncrementerException(
					"Passed a null IBitString to a constructor.");

		// smaller is a subset of larger
		IProblem p1 = new BitStringListSubsetter(smaller, larger);
		// singleton is not in smaller
		IProblem p2 = new BitStringListNonmembershipper(singleton, smaller);
		// singleton is in larger
		IProblem p3 = new BitStringListMembershipper(singleton, larger);
		// each y in larger is in smaller or is equal to singleton
		IProblem[] disj = new IProblem[larger.size()];
		for (int i = 0; i < larger.size(); i++)
		{
			IBitString curr = larger.getBitString(i);
			IProblem q1 = new BitStringListMembershipper(curr, smaller);
			IProblem q2 = new BitStringEqualizer(curr, singleton);
			disj[i] = new Disjunction(q1, q2);
		}

		IProblem problem = new Conjunction(p1, p2, p3, new Conjunction(disj));

		this.setClauses(problem.getClauses());
	}
}

/*
 * package positronic.satisfiability.arrays;
 * 
 * import positronic.satisfiability.elements.Conjunction; import
 * positronic.satisfiability.elements.Disjunction; import
 * positronic.satisfiability.elements.IProblem; import
 * positronic.satisfiability.elements.Problem; import
 * positronic.satisfiability.exceptions.BitStringListIncrementerException;
 * 
 * public class BitStringListIncrementer extends Problem implements IProblem {
 * private static final long serialVersionUID = 1L;
 * 
 * public BitStringListIncrementer(IBitStringList X, IBitString I,
 * IBitStringList Y) throws Exception { if(X==null || Y==null) throw new
 * BitStringListIncrementerException
 * ("Passed a null IBitStringList to a constructor."); if(I==null) throw new
 * BitStringListIncrementerException
 * ("Passed a null IBitString to a constructor."); IProblem problem=null;
 * for(int i=0;i<X.size();i++) { IBitString curr=X.getBitString(i); IProblem
 * p1=new Disjunction( new BitStringListNonmembershipper(curr,Y), new
 * BitStringListMembershipper(curr,X), new BitStringEqualizer(curr,I));
 * problem=new Conjunction(problem,p1); IProblem p2=new Disjunction( new
 * BitStringListNonmembershipper(curr,X), new BitStringUnequalizer(curr,I));
 * problem=new Conjunction(problem,p2); IProblem p3=new Disjunction( new
 * BitStringListMembershipper(curr,Y), new
 * BitStringListNonmembershipper(curr,X)); IProblem p4=new Disjunction( new
 * BitStringListMembershipper(curr,Y), new BitStringUnequalizer(curr,I));
 * problem=new Conjunction(problem,p3,p4); } for(int i=0;i<Y.size();i++) {
 * IBitString curr=Y.getBitString(i); IProblem p1=new Disjunction( new
 * BitStringListNonmembershipper(curr,Y), new
 * BitStringListMembershipper(curr,X), new BitStringEqualizer(curr,I));
 * problem=new Conjunction(problem,p1); IProblem p2=new Disjunction( new
 * BitStringListNonmembershipper(curr,X), new BitStringUnequalizer(curr,I));
 * problem=new Conjunction(problem,p2); IProblem p3=new Disjunction( new
 * BitStringListMembershipper(curr,Y), new
 * BitStringListNonmembershipper(curr,X)); IProblem p4=new Disjunction( new
 * BitStringListMembershipper(curr,Y), new BitStringUnequalizer(curr,I));
 * problem=new Conjunction(problem,p3,p4); }
 * 
 * IBitString curr=I; IProblem p1=new Disjunction( new
 * BitStringListNonmembershipper(curr,Y), new
 * BitStringListMembershipper(curr,X), new BitStringEqualizer(curr,I));
 * problem=new Conjunction(problem,p1); IProblem p2=new Disjunction( new
 * BitStringListNonmembershipper(curr,X), new BitStringUnequalizer(curr,I));
 * problem=new Conjunction(problem,p2); IProblem p3=new Disjunction( new
 * BitStringListMembershipper(curr,Y), new
 * BitStringListNonmembershipper(curr,X)); IProblem p4=new Disjunction( new
 * BitStringListMembershipper(curr,Y), new BitStringUnequalizer(curr,I));
 * problem=new Conjunction(problem,p3,p4);
 * 
 * this.setClauses(problem.getClauses()); } }
 */