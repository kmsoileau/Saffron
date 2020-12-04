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

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.BitStringArraySplitter;
import bits.strings.BitStringFixer;

public class BitStringArraySplitterDemo
{
	public static void main(String[] args) throws Exception
	{
		/**
		 * Set Java variables:
		 */

		/**
		 * Set globals:
		 */

		/**
		 * Create Saffron objects and arrays:
		 */

		IBitString[] C = new IBitString[]
		{ new BitString("01001"), new BitString("10111"), new BitString("10101"), new BitString("01100"),
				new BitString("11101") };

		int size = C[0].size();

		IBitString S1 = new BitString(size);
		IBitString S2 = new BitString(size);

		/**
		 * Create problems which constrain the values of these Saffron objects:
		 */

		IProblem p1 = new BitStringFixer(C);
		IProblem p2 = new BitStringArraySplitter(C, S1, S2);

		/**
		 * Create the IProblem of satisfying all of these constraining problems:
		 */

		IProblem problem = new Conjunction(p1, p2);

		/**
		 * Solve the IProblem:
		 */

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("S1= " + S1.toBits());
			System.out.println("S2= " + S2.toBits());
		}
		else
			System.out.println("No solution.");
	}
}
