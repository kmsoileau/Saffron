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
package demos.naturalnumbers.rectangles;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.NaturalNumber;
import naturalnumbers.rectangles.INaturalNumberRectangle;
import naturalnumbers.rectangles.NaturalNumberRectangle;
import naturalnumbers.rectangles.NaturalNumberRectangleCongruenter;
import naturalnumbers.rectangles.NaturalNumberRectangleFixer;

/**
 * 
 *
 */
public class NaturalNumberRectangleCongruenterDemo1
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumberRectangle rect1 = new NaturalNumberRectangle("rect1");
		INaturalNumberRectangle rect2 = new NaturalNumberRectangle("rect2");

		INaturalNumber X = new NaturalNumber();
		INaturalNumber Y = new NaturalNumber();

		IProblem problem = new Conjunction(new NaturalNumberRectangleFixer(rect1, 1, 2, 3, 7),
				new NaturalNumberRectangleFixer(rect2, 4, 5, 3, 7),
				new NaturalNumberRectangleCongruenter(rect1, rect2, X, Y));

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("A = " + rect1);
			System.out.println("B = " + rect2);
			System.out.println("X = " + X);
			System.out.println("Y = " + Y);
		}
		else
			System.out.println("No solution.");
	}
}
