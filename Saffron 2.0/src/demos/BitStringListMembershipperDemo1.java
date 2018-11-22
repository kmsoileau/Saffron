package demos;

import java.util.List;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IBooleanLiteral;
import bits.IProblem;
import bits.Problem;
import bitstringlists.BitStringList;
import bitstringlists.BitStringListFixer;
import bitstringlists.BitStringListMembershipper;
import bitstringlists.IBitStringList;
import bitstrings.BitString;
import bitstrings.BitStringFixer;

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
		{ new BitString("000"), new BitString("101"), new BitString("110"),
				new BitString("100"), new BitString("111"),
				new BitString("010") });

		/**
		 * Note: To get a successful match, it is necessary for the matching
		 * string to have the same length and the same content as the search
		 * string.
		 */
		IBitString string = new BitString("string", new boolean[3]);
		IProblem stringfix = new BitStringFixer(string, new boolean[]
		{ true, false, true });

		IProblem bslm = new BitStringListMembershipper(string, bsl);
		System.out.println(bslm);
		IProblem fix = new BitStringListFixer(bsl);

		IProblem problem = new Conjunction(bslm, fix, stringfix);

		System.out.println(problem);
		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println(string);
		}
		else
			System.out.println("No solution.");
	}
}