package demos.bitstringlists;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bitstringlists.BitStringList;
import bitstringlists.BitStringListFixer;
import bitstringlists.BitStringListSubsetter;
import bitstringlists.IBitStringList;
import bitstrings.BitString;

/**
 * <p>
 * Title: BitStringListSubsetterDemo
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

public class BitStringListSubsetterDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitStringList slm = new BitStringList("y", new IBitString[]
		{ new BitString("101"), new BitString("010"), new BitString("111"), });

		IBitStringList bsl = new BitStringList("slist", new IBitString[]
		{ new BitString("101"), new BitString("010"), new BitString("011"),
				new BitString("100"), new BitString("111"),
				new BitString("000"), });

		IProblem problem = new Conjunction(new BitStringListFixer(slm),
				new BitStringListFixer(bsl), new BitStringListSubsetter(slm,
						bsl));

		System.out.println(problem);

		IProblemMessage s = problem.findModel(Problem.defaultSolver());

		if (s.getStatus() == IProblemMessage.SATISFIABLE
				&& s.getLiterals().size() > 0)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println(slm);
			System.out.println(bsl);
		}
		else
			System.out.println("No solution.");
	}
}