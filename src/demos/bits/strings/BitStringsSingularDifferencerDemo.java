/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Jan 21, 2019
 */
package demos.bits.strings;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.BitStringFixer;
import bits.strings.BitStringsSingularDifferencer;

/**
 * 
 *
 */
public class BitStringsSingularDifferencerDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitString B1 = new BitString("00001001");
		IBitString B2 = new BitString("01001001");

		IProblem problem = new Conjunction(new BitStringsSingularDifferencer(B1, B2), new BitStringFixer(B1),
				new BitStringFixer(B2));

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("B1= " + B1.toBits());
			System.out.println("B2= " + B2.toBits());
		}
		else
			System.out.println("No solution.");
	}
}
