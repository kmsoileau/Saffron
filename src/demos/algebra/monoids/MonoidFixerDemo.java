/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 11, 2019
 */
package demos.algebra.monoids;

import algebra.monoids.generalized.Monoid;
import algebra.monoids.generalized.MonoidFixer;
import bits.BooleanLiteral;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;

/**
 * 
 *
 */
public class MonoidFixerDemo
{
	public static void main(String[] args) throws Exception
	{
		Monoid mnd = new Monoid(new int[][]
		{
				{ 0, 0, 0 },
				{ 0, 3, 3 },
				{ 0, 2, 2 },
				{ 0, 1, 1 },
				{ 3, 0, 3 },
				{ 3, 3, 0 },
				{ 3, 2, 1 },
				{ 3, 1, 2 },
				{ 2, 0, 2 },
				{ 2, 3, 1 },
				{ 2, 2, 0 },
				{ 2, 1, 3 },
				{ 1, 0, 1 },
				{ 1, 3, 2 },
				{ 1, 2, 3 },
				{ 1, 1, 0 } });

		INaturalNumber X = new NaturalNumber(2);

		new NaturalNumberFixer(X);

		IProblem fix = new MonoidFixer(mnd);

		IProblemMessage s = fix.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());

			System.out.println(mnd);

			int[][] array = mnd.toOpTable();
			for (int i = 0; i < array.length; i++)
				System.out.println("{ " + array[i][0] + "," + array[i][1] + "," + array[i][2] + " }");
		}
		else
			System.out.println("There is no solution.");
	}
}
