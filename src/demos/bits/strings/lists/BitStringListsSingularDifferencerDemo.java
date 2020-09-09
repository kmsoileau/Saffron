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
package demos.bits.strings.lists;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.lists.BitStringList;
import bits.strings.lists.BitStringListFixer;
import bits.strings.lists.BitStringListsSingularDifferencer;
import bits.strings.lists.IBitStringList;

/**
 * 
 *
 */
public class BitStringListsSingularDifferencerDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitStringList B1 = new BitStringList("A", new IBitString[]
		{ new BitString("000"), new BitString("011"), new BitString("110"), new BitString("010") });

		IBitStringList B2 = new BitStringList("B", new IBitString[]
		{ new BitString("000"), new BitString("011"), new BitString("010"), new BitString("010") });

		IProblem problem = new Conjunction(new BitStringListsSingularDifferencer(B1, B2), new BitStringListFixer(B1),
				new BitStringListFixer(B2));

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
