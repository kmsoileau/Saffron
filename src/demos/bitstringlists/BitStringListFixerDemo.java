package demos.bitstringlists;

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
import bits.IBitString;
import bits.IBooleanLiteral;
import bits.IProblem;
import bits.Problem;
import bitstringlists.BitStringList;
import bitstringlists.BitStringListFixer;
import bitstringlists.IBitStringList;
import bitstrings.BitString;

public class BitStringListFixerDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitString[] theStrings = new BitString[3];
		theStrings[0] = new BitString(new boolean[]
		{ true, true, false });
		theStrings[1] = new BitString("named", new boolean[]
		{ true, true, true, false });
		theStrings[2] = new BitString(new boolean[]
		{ false, true, true });
		IBitStringList samp2 = new BitStringList("theList", theStrings);
		IProblem bslf2 = new BitStringListFixer(samp2);
		System.out.println(bslf2);
		List<IBooleanLiteral> s = bslf2.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("samp2= " + samp2);

		}
		else
			System.out.println("No solution.");

	}
}