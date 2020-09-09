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
package demos.bits.strings.lists;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.lists.BitStringList;
import bits.strings.lists.BitStringListDistincter;
import bits.strings.lists.BitStringListFixer;
import bits.strings.lists.IBitStringList;

public class BitStringListDistincterDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitStringList C = new BitStringList(new IBitString[]
		{ new BitString("01001010"), new BitString("10101010"), new BitString("00001000"), new BitString("00100000"),
				new BitString("00101000") });

		IProblem problem = new Conjunction(new BitStringListDistincter(C), new BitStringListFixer(C));

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
