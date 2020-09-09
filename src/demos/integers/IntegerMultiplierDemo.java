/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 13, 2019
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
import integers.IntegerMultiplier;
import naturalnumbers.NaturalNumber;

/**
 * 
 *
 */
public class IntegerMultiplierDemo
{
	public static void main(String[] args) throws Exception
	{
		NaturalNumber.setLargestNaturalNumber(500);

		IInteger X = new Integer("X", -29);
		IInteger Y = new Integer("Y", -13);
		IInteger Z = new Integer("Z");

		IProblem p = new Conjunction(new IProblem[]
		{ new IntegerMultiplier(X, Y, Z), new IntegerFixer(X), new IntegerFixer(Y) });

		System.out.println(p);

		IProblemMessage s = p.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());

			System.out.println("X = " + X);
			System.out.println("Y = " + Y);
			System.out.println("Z = " + Z);
		}
		else
			System.out.println("No solution.");
	}
}
