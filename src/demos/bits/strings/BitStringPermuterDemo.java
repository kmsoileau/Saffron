/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 7, 2019
 */
package demos.bits.strings;

import bits.BooleanLiteral;
import bits.IBitString;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.BitStringPermuter;

/**
 * 
 *
 */
public class BitStringPermuterDemo
{
	public static void main(String[] args) throws Exception
	{
		int bits = 7;

		IBitString[] M = new IBitString[bits];
		for (int i = 0; i < bits; i++)
			M[i] = new BitString(bits);

		IProblem problem = new BitStringPermuter(M);

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			for (int i = 0; i < bits; i++)
				System.out.println(M[i].toBits());
		}
		else
			System.out.println("No solution.");
	}
}
