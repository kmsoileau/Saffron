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
package demos.algebra.groups.generalized;

import algebra.groups.generalized.Group;
import algebra.groups.generalized.GroupAssociativer;
import algebra.groups.generalized.GroupFixer;
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
public class GroupAssociativerDemo
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

		Group g = new Group(opTable);

		System.out.println(g);

		INaturalNumber X = new NaturalNumber();
		INaturalNumber Y = new NaturalNumber();
		INaturalNumber Z = new NaturalNumber();

		IProblem problem = new Conjunction(new IProblem[]
		{ new GroupFixer(g), new NaturalNumberFixer(X, 1), new NaturalNumberFixer(Y, 3), new NaturalNumberFixer(Z, 1),
				new GroupAssociativer(g, X, Y, Z) });

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
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
