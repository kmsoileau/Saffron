package demos.bits.strings.lists;

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
 * Title: BitStringListMembershipperDemo3
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

public class BitStringListMembershipperDemo3
{
	public static void main(String[] args) throws Exception
	{
		IBitString slm = new BitString("Kerry", "011");

		IBitStringList bsl = new BitStringList("slist", new IBitString[]
		{ new BitString("000"), new BitString("010"), new BitString("001"),
				new BitString("011"), new BitString("111"),
				new BitString("101"), new BitString("000"),
				new BitString("000"), });

		IProblem problem = new Conjunction(new BitStringFixer(slm),
				new BitStringListFixer(bsl), new BitStringListMembershipper(
						slm, bsl));

		System.out.println(problem);

		IProblemMessage s = problem.findModel(Problem.defaultSolver());

		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			System.out.println("slm= " + slm);
			System.out.println("bsl= " + bsl);
		}
		else
			System.out.println("No solution.");

		// System.out.print(((Problem)problem).reduceComplements());
	}
}