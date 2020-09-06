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
package demos.algebra.groups.generalized;

import algebra.groups.generalized.Group;
import algebra.groups.generalized.GroupFixer;
import algebra.groups.generalized.GroupIdentityer;
import algebra.groups.generalized.GroupInverser;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;

/**
 * 
 *
 */
public class GroupFixerDemo2
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

		INaturalNumber Identity = new NaturalNumber();

		INaturalNumber X = new NaturalNumber();
		INaturalNumber XInv = new NaturalNumber();

		IProblemMessage s = new Conjunction(
				new Conjunction(new GroupFixer(g), new GroupIdentityer(g,
						Identity), new NaturalNumberFixer(X, 3)),
				new GroupInverser(g, X, XInv, Identity)).findModel(Problem
				.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());

			System.out.println("Group = " + g);
			System.out.println("Identity = " + Identity);
			System.out.println("X = " + X);
			System.out.println("X^(-1) = " + XInv);
		}
		else
			System.out.println("There is no solution.");
	}
}
