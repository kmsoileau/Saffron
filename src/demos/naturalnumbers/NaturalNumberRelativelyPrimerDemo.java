/**
 * 
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Jan 9, 2019
 */
package demos.naturalnumbers;

import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.NaturalNumberRelativelyPrimer;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

public class NaturalNumberRelativelyPrimerDemo
{
	public static void main(String[] args) throws Exception
	{
		NaturalNumber.setLargestNaturalNumber(600);
		INaturalNumber X = new NaturalNumber();
		INaturalNumber Y = new NaturalNumber();
		INaturalNumber A = new NaturalNumber();
		INaturalNumber B = new NaturalNumber();

		IProblem problem = new Conjunction(new IProblem[]
		{ new NaturalNumberFixer(X, 35), new NaturalNumberFixer(Y, 22),
				new NaturalNumberRelativelyPrimer(X, Y, A, B) });

		System.out.println(problem);

		IProblemMessage s = problem.findModel(Problem.defaultSolver());

		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.print("\nX = " + X);
			System.out.print("\tA = " + A);
			System.out.print("\nY = " + Y);
			System.out.print("\tB = " + B);
		}
		else
			System.out.println("No solution.");
	}
}
