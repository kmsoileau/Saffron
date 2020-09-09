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

import java.util.ArrayList;

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

public class BitStringBitAtomerDemo extends Problem implements IProblem
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

		IBitString atomBitString = new BitString(C[0].size());
		ArrayList<String> atoms = new ArrayList<String>();
		for (int pos = 0; pos < C[0].size(); pos++)
		{
			IProblemMessage s = new Conjunction(new BitStringFixer(C), new BitStringBitAtomer(C, pos, atomBitString))
					.findModel(Problem.defaultSolver());
			if (s.getStatus() == IProblemMessage.SATISFIABLE)
			{
				BooleanLiteral.interpret(s.getLiterals());
				// System.out.println("Atom(" + pos + ")=\t"
				// + atomBitString.toBits());
				String hsb = atomBitString.toBits();
				if (!atoms.contains(hsb))
				{
					atoms.add(hsb);
				}
			}
			else
				System.out.println("No solution.");
		}

		System.out.println("\nOUTPUT ATOMS:");
		for (int i = 0; i < atoms.size(); i++)
		{
			System.out.println(atoms.get(i));
		}
	}
}
