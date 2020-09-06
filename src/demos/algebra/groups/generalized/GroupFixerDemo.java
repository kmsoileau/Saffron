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
import bits.BooleanLiteral;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

/**
 * 
 *
 */
public class GroupFixerDemo
{
	public static void main(String[] args) throws Exception
	{
		Group grp = new Group(new int[][]
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

		IProblem fix = new GroupFixer(grp);

		IProblemMessage s = fix.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());

			System.out.println(grp);

			int[][] array = grp.toOpTable();
			for (int i = 0; i < array.length; i++)
				System.out.println("{ " + array[i][0] + "," + array[i][1] + ","
						+ array[i][2] + " }");
		}
		else
			System.out.println("There is no solution.");
	}
}
