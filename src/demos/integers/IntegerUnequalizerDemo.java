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
import integers.IntegerFixer;
import integers.IntegerUnequalizer;

/**
 * 
 *
 */
public class IntegerUnequalizerDemo
{
	public static void main(String[] args) throws Exception
	{
		IInteger A = new Integer("A", 12);
		IInteger B = new Integer("B", -9);

		IProblem p1 = new IntegerFixer(A);
		IProblem p2 = new IntegerFixer(B);
		IProblem p3 = new IntegerUnequalizer(A, B);

		IProblem problem = new Conjunction(p1, p2, p3);

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println(A.getName() + " = " + A);
			System.out.println(B.getName() + " = " + B);
		}
		else
			System.out.println("No solution.");
	}
}
