/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 21, 2019
 */
package demos.algebra.magmas;

import algebra.magmas.generalized.Magma;
import algebra.magmas.generalized.MagmaFixer;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

/**
 * 
 *
 */
public class MagmaFixerDemo
{

	public static void main(String[] args) throws Exception
	{
		int[][] opTable = new int[][]
		{
				{ 0, 0, 0 },
				{ 0, 1, 1 },
				{ 0, 2, 2 },
				{ 0, 3, 3 },
				{ 1, 0, 1 },
				{ 1, 1, 0 },
				{ 1, 2, 3 },
				{ 1, 3, 2 },
				{ 2, 0, 2 },
				{ 2, 1, 3 },
				{ 2, 2, 0 },
				{ 2, 3, 1 },
				{ 3, 0, 3 },
				{ 3, 1, 2 },
				{ 3, 2, 1 },
				{ 3, 3, 0 } };

		Magma mgm = new Magma(opTable);

		System.out.println(mgm);

		IProblem problem = new Conjunction(new MagmaFixer(mgm));

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println(mgm);
		}
		else
			System.out.println("No solution.");
	}

}
