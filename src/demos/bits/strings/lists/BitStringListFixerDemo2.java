package demos.bits.strings.lists;

/**
 * <p>Title: TBS</p>
 * <p>Description: TBS</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.BitStringFixer;

public class BitStringListFixerDemo2
{
	public static void main(String[] args) throws Exception
	{
		IBitString[] listOfSites = new BitString[9];
		for (int i = 0; i < 9; i++)
			listOfSites[i] = new BitString(4);
		IProblem[] constraints = new BitStringFixer[]
		{ new BitStringFixer(listOfSites[0], new boolean[]
				{ false, false, false, false }), new BitStringFixer(listOfSites[1], new boolean[]
				{ true, false, false, false }), new BitStringFixer(listOfSites[2], new boolean[]
				{ false, true, false, false }), new BitStringFixer(listOfSites[3], new boolean[]
				{ true, true, false, false }), new BitStringFixer(listOfSites[4], new boolean[]
				{ false, false, true, false }), new BitStringFixer(listOfSites[5], new boolean[]
				{ true, false, true, false }), new BitStringFixer(listOfSites[6], new boolean[]
				{ false, true, true, false }), new BitStringFixer(listOfSites[7], new boolean[]
				{ true, true, true, false }), new BitStringFixer(listOfSites[8], new boolean[]
				{ false, false, false, true }) };

		IProblem bslf2 = new Conjunction(constraints);
		System.out.println(bslf2);
		IProblemMessage s = bslf2.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			for (int i = 0; i < s.getLiterals().size(); i++)
				System.out.println(s.getLiterals().get(i));
			BooleanLiteral.interpret(s.getLiterals());
			for (int i = 0; i < listOfSites.length; i++)
				System.out.println(listOfSites[i]);
		}
		else
			System.out.println("No solution.");
	}
}