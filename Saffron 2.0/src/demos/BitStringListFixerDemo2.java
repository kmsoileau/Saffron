package demos;

/**
 * <p>Title: TBS</p>
 * <p>Description: TBS</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */
import java.util.List;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IBooleanLiteral;
import bits.IProblem;
import bits.Problem;
import bitstrings.BitString;
import bitstrings.BitStringFixer;

public class BitStringListFixerDemo2
{
	public static void main(String[] args) throws Exception
	{
		IBitString[] listOfSites = new BitString[9];
		for (int i = 0; i < 9; i++)
			listOfSites[i] = new BitString(4);
		IProblem[] constraints = new BitStringFixer[]
		{ new BitStringFixer(listOfSites[0], new boolean[]
		{ false, false, false, false }),
				new BitStringFixer(listOfSites[1], new boolean[]
				{ true, false, false, false }),
				new BitStringFixer(listOfSites[2], new boolean[]
				{ false, true, false, false }),
				new BitStringFixer(listOfSites[3], new boolean[]
				{ true, true, false, false }),
				new BitStringFixer(listOfSites[4], new boolean[]
				{ false, false, true, false }),
				new BitStringFixer(listOfSites[5], new boolean[]
				{ true, false, true, false }),
				new BitStringFixer(listOfSites[6], new boolean[]
				{ false, true, true, false }),
				new BitStringFixer(listOfSites[7], new boolean[]
				{ true, true, true, false }),
				new BitStringFixer(listOfSites[8], new boolean[]
				{ false, false, false, true }) };

		IProblem bslf2 = new Conjunction(constraints);
		System.out.println(bslf2);
		List<IBooleanLiteral> s = bslf2.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			for (int i = 0; i < s.size(); i++)
				System.out.println(s.get(i));
			BooleanLiteral.interpret(s);
			for (int i = 0; i < listOfSites.length; i++)
				System.out.println(listOfSites[i]);
		}
		else
			System.out.println("No solution.");
	}
}