/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 11, 2019
 */
package demos.bits.strings;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.BitStringArrayDistincter;
import bits.strings.BitStringFixer;

public class BitStringArrayDistincterDemo extends Problem implements IProblem
{
	public static void main(String[] args) throws Exception
	{
		IBitString[] C = new IBitString[]
		{ new BitString("01001010"), new BitString("10101010"), new BitString("00001000"), new BitString("00100000"),
				new BitString("00101000") };

		IProblem problem = new Conjunction(new BitStringArrayDistincter(C), new BitStringFixer(C));

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("All elements are distinct.");
		}
		else
			System.out.println("Some or all elements are equal.");

	}
}
