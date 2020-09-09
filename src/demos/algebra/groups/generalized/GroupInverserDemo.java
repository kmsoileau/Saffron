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
import algebra.groups.generalized.GroupFixer;
import algebra.groups.generalized.GroupInverser;
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
public class GroupInverserDemo
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

		INaturalNumber EInverse = new NaturalNumber();
		INaturalNumber E = new NaturalNumber();
		INaturalNumber Identity = new NaturalNumber(0);

		IProblem problem = new Conjunction(new GroupFixer(g), new NaturalNumberFixer(E, 3),
				new NaturalNumberFixer(Identity), new GroupInverser(g, E, EInverse, Identity));

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("E = " + E);
			System.out.println("EInverse = " + EInverse);
			System.out.println("Identity = " + Identity);
		}
		else
			System.out.println("No solution.");
	}
}
