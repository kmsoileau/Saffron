/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 4, 2019
 */
package demos.integers.intervals;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import integers.intervals.IIntegerInterval;
import integers.intervals.IntegerInterval;
import integers.intervals.IntegerIntervalCongruenter;

/**
 * 
 *
 */
public class IntegerIntervalCongruenterDemo2 extends Problem implements IProblem
{
	public static void main(String[] args) throws Exception
	{
		IIntegerInterval A = new IntegerInterval("A");
		IIntegerInterval B = new IntegerInterval("B");

		IProblem problem = new Conjunction(new IntegerIntervalFixer(A, 3, 7), new IntegerIntervalFixer(B, 7, 11),
				new IntegerIntervalCongruenter(A, B));

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("A = " + A);
			System.out.println("B = " + B);

		}
		else
			System.out.println("No solution.");
	}
}
