/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 10, 2019
 */
package demos.integers;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import integers.IInteger;
import integers.Integer;
import integers.IntegerEqualizer;
import integers.IntegerFixer;

/**
 * 
 *
 */
public class IntegerEqualizerDemo
{
	public static void main(String[] args) throws Exception
	{
		IInteger A = new Integer();
		IInteger B = new Integer();
		IInteger C = new Integer();

		IProblem problem = new Conjunction(new IntegerFixer(A, -13), new IntegerEqualizer(A, B));

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());

			System.out.println("A = " + A);
			System.out.println("B = " + B);
			System.out.println("C = " + C);
		}
		else
			System.out.println("No solution.");
	}
}
