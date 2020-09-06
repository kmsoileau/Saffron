package demos.naturalnumbers.lists;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.NaturalNumber;
import naturalnumbers.lists.INaturalNumberList;
import naturalnumbers.lists.NaturalNumberList;
import naturalnumbers.lists.NaturalNumberListFixer;
import naturalnumbers.lists.NaturalNumberListMembership;

/**
 * <p>
 * Title: NaturalNumberListMembershipDemo1
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
public class NaturalNumberListMembershipDemo1
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumberList bsl = new NaturalNumberList("slist",
				new INaturalNumber[]
				{ new NaturalNumber(100), new NaturalNumber(2),
						new NaturalNumber(3) });
		System.out.println(bsl);

		INaturalNumber string = new NaturalNumber("string");

		IProblem bslm = new NaturalNumberListMembership(string, bsl);
		IProblem fix = new NaturalNumberListFixer(bsl);

		IProblem problem = new Conjunction(bslm, fix);
		System.out.println(problem);

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("string = " + string);
		}
		else
			System.out.println("No solution.");
	}
}