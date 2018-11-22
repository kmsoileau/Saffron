package demos;

import java.util.List;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import naturalnumberlists.INaturalNumberList;
import naturalnumberlists.NaturalNumberList;
import naturalnumberlists.NaturalNumberListFixer;
import naturalnumberlists.NaturalNumberListMembership;
import naturalnumbers.NaturalNumber;

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

		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("string = " + string);
		}
		else
			System.out.println("No solution.");
	}
}