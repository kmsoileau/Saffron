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
import bits.strings.lists.BitStringListNonmembershipper;
import bits.strings.lists.IBitStringList;

/**
 * <p>
 * Title: BitStringListNonmembershipperDemo
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

public class BitStringListNonmembershipperDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitString slm = new BitString("y", new boolean[]
		{ false, true, false, false });

		IBitStringList bsl = new BitStringList("slist", new IBitString[]
		{ new BitString("000"), new BitString("010"), new BitString("0101"),
				new BitString("011"), new BitString("101"),
				new BitString("000"), new BitString("000"), });

		IProblem problem = new Conjunction(new BitStringFixer(slm),
				new BitStringListFixer(bsl), new BitStringListNonmembershipper(
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
	}
}