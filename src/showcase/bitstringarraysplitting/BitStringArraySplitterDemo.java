/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Dec 17, 2018
 */
package showcase.bitstringarraysplitting;

import java.util.List;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IBooleanLiteral;
import bits.IProblem;
import bits.Problem;
import bitstrings.BitString;
import bitstrings.BitStringArraySplitter;
import bitstrings.BitStringFixer;

public class BitStringArraySplitterDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitString[] C = new IBitString[]
		{ new BitString("01001"), new BitString("10111"),
				new BitString("10101"), new BitString("01100"),
				new BitString("11101") };

		int size = C[0].size();

		IBitString S1 = new BitString(size);
		IBitString S2 = new BitString(size);

		IProblem problem = new Conjunction(new BitStringFixer(C),
				new BitStringArraySplitter(C, S1, S2));

		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("S1= " + S1.toBits());
			System.out.println("S2= " + S2.toBits());
		}
		else
			System.out.println("No solution.");
	}
}
