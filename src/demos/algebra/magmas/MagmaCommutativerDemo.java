/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 12, 2019
 */
package demos.algebra.magmas;

import algebra.magmas.generalized.Magma;
import algebra.magmas.generalized.MagmaCommutativer;
import algebra.magmas.generalized.MagmaFixer;
import bits.BooleanLiteral;
import bits.Conjunction;
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
public class MagmaCommutativerDemo
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

		INaturalNumber X = new NaturalNumber();
		INaturalNumber Y = new NaturalNumber();

		IProblem problem = new Conjunction(
				new IProblem[]
				{ new MagmaFixer(mgm), new NaturalNumberFixer(X, 1),
						new NaturalNumberFixer(Y, 3),
						new MagmaCommutativer(mgm, X, Y) });

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("X = " + X);
			System.out.println("Y = " + Y);
		}
		else
			System.out.println("No solution.");
	}
}
