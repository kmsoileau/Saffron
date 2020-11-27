/**
 * 
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Dec 16, 2018
 */
package showcase.bitstringbitatom;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.BitStringBitAtomer;
import bits.strings.BitStringFixer;
import utility.Bitstrings;

public class BitStringBitAtomerDemo2 extends Problem implements IProblem
{
	public static void main(String[] args) throws Exception
	{
		IBitString[] C = new IBitString[5];
		for (int i = 0; i < 5; i++)
		{
			String str = Bitstrings.randomBitstring(12);
			C[i] = new BitString(str);
		}

		System.out.println("INPUT STRINGS:");
		for (int i = 0; i < C.length; i++)
		{
			System.out.println(C[i].toBits());
		}

		IBitString[] atomBitString = new IBitString[C[0].size()];
		for (int i = 0; i < atomBitString.length; i++)
		{
			atomBitString[i] = new BitString(C[0].size());
		}

		IProblemMessage s = new Conjunction(new BitStringFixer(C), new BitStringBitAtomer(C, atomBitString))
				.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("\nOUTPUT ATOMS:");
			for (int i = 0; i < atomBitString.length; i++)
			{
				System.out.println(atomBitString[i].toBits());
			}
		}
		else
			System.out.println("No solution.");

	}
}
