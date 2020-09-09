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
import integers.IntegerFixer;
import integers.IntegerSubtracter;
import naturalnumbers.NaturalNumber;

/**
 * 
 *
 */
public class IntegerSubtracterDemo
{
	public static void main(String[] args) throws Exception
	{
		NaturalNumber.setLargestNaturalNumber(100);

		IInteger A = new integers.Integer("A");
		IInteger B = new integers.Integer("B");
		IInteger C = new integers.Integer("C");

		IProblem problem = new Conjunction(new IntegerFixer(A, 23), new IntegerFixer(C, -27),
				new IntegerSubtracter(A, B, C));

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
