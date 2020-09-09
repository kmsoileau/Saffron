package demos.naturalnumbers.lists;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberUnequalizer;
import naturalnumbers.lists.INaturalNumberList;
import naturalnumbers.lists.NaturalNumberList;
import naturalnumbers.lists.NaturalNumberListFixer;
import naturalnumbers.lists.NaturalNumberListMembership;

/**
 * <p>
 * Title: NaturalNumberListMembershipDemo2
 * </p>
 * <p>
 * Description: TBS
 * </p>
 * <p>
 * Copyright (c) 2005
 * </p>
 * <p>
 * Company: Positronic Software
 * </p>
 * 
 * @author Kerry Michael Soileau
 * @version 1.0
 */

public class NaturalNumberListMembershipDemo2
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumber string1 = new NaturalNumber("string1");
		INaturalNumber string2 = new NaturalNumber("string2");

		INaturalNumberList bsl = new NaturalNumberList("slist", new INaturalNumber[]
		{ new NaturalNumber(11), new NaturalNumber(15), new NaturalNumber(14), new NaturalNumber(4),
				new NaturalNumber(7), new NaturalNumber(6) });

		// The list must have given values
		IProblem fix = new NaturalNumberListFixer(bsl);

		// Both strings must occur in the list
		IProblem bslm1 = new NaturalNumberListMembership(string1, bsl);
		IProblem bslm2 = new NaturalNumberListMembership(string2, bsl);
		// The two strings must be different
		IProblem diff = new NaturalNumberUnequalizer(string1, string2);
		// Combine all of the IProblems into a single IProblem
		IProblem problem = new Conjunction(bslm1, bslm2, fix, diff);
		problem.sort();

		System.out.println(problem);

		// Solve the IProblem
		IProblemMessage s = problem.findModel(Problem.defaultSolver());

		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			for (int i = 0; i < s.getLiterals().size(); i++)
				System.out.println(s.getLiterals().get(i));
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("bsl = " + bsl);
			System.out.println("String1 = " + string1);
			System.out.println("String2 = " + string2);
		}
		else
			System.out.println("No solution.");
	}
}