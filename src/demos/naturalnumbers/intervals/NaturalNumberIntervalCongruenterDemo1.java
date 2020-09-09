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
package demos.naturalnumbers.intervals;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.NaturalNumber;
import naturalnumbers.intervals.INaturalNumberInterval;
import naturalnumbers.intervals.NaturalNumberInterval;
import naturalnumbers.intervals.NaturalNumberIntervalCongruenter;
import naturalnumbers.intervals.NaturalNumberIntervalFixer;

/**
 * 
 *
 */
public class NaturalNumberIntervalCongruenterDemo1
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumberInterval A = new NaturalNumberInterval("A");
		INaturalNumberInterval B = new NaturalNumberInterval("B");

		INaturalNumber X = new NaturalNumber();

		IProblem problem = new Conjunction(new NaturalNumberIntervalFixer(A, 3, 7),
				new NaturalNumberIntervalFixer(B, 7, 11), new NaturalNumberIntervalCongruenter(A, B, X));

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("A = " + A);
			System.out.println("B = " + B);
			System.out.println("X = " + X);
		}
		else
			System.out.println("No solution.");
	}
}
