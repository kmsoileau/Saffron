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
package demos.bitstrings;

import java.util.List;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IBooleanLiteral;
import bits.IProblem;
import bits.Problem;
import bitstrings.BitString;
import bitstrings.BitStringBitHuller;
import bitstrings.BitStringFixer;

public class BitStringBitHullerDemo extends Problem implements IProblem
{
	public static void main(String[] args) throws Exception
	{
		IBitString[] C = new IBitString[]
		{ new BitString("111100000000"), new BitString("001111110000"),
				new BitString("000000111100"), new BitString("000000000011") };

		IBitString hullBitString = new BitString(C[0].size());
		for (int pos = 0; pos < C[0].size(); pos++)
		{
			List<IBooleanLiteral> s = new Conjunction(new BitStringFixer(C),
					new BitStringBitHuller(C, pos, hullBitString))
					.findModel(Problem.defaultSolver());
			if (s != null && s.size() > 0)
			{
				BooleanLiteral.interpret(s);
				System.out.println("Hull(" + pos + ")= "
						+ hullBitString.toBits());
			}
			else
				System.out.println("No solution.");
		}
	}
}
