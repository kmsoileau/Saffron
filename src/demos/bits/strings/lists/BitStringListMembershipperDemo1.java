package demos.bits.strings.lists;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.BitStringFixer;
import bits.strings.lists.BitStringList;
import bits.strings.lists.BitStringListFixer;
import bits.strings.lists.BitStringListMembershipper;
import bits.strings.lists.IBitStringList;

/**
 * <p>
 * Title: BitStringListMembershipperDemo1
 * </p>
 * <p>
 * Description: This is a sample application showing the use of the
 * BitStringListMembershipper class.
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

// Need to reverify this one
public class BitStringListMembershipperDemo1
{
	public static void main(String[] args) throws Exception
	{
		IBitStringList bsl = new BitStringList("slist", new IBitString[]
		{ new BitString("000"), new BitString("101"), new BitString("110"), new BitString("100"), new BitString("111"),
				new BitString("010") });

		/**
		 * Note: To get a successful match, it is necessary for the matching string to
		 * have the same length and the same content as the search string.
		 */
		IBitString string = new BitString("string", new boolean[3]);
		IProblem stringfix = new BitStringFixer(string, new boolean[]
		{ true, false, true });

		IProblem bslm = new BitStringListMembershipper(string, bsl);
		System.out.println(bslm);
		IProblem fix = new BitStringListFixer(bsl);

		IProblem problem = new Conjunction(bslm, fix, stringfix);

		System.out.println(problem);
		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println(string);
		}
		else
			System.out.println("No solution.");
	}
}