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
package demos.algebra.semigroups;

import algebra.semigroups.generalized.Semigroup;
import algebra.semigroups.generalized.SemigroupAssociativer;
import bits.BooleanLiteral;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

/**
 * 
 *
 */
public class SemigroupAssociativerDemo2
{
	public static void main(String[] args) throws Exception
	{
		int[][] opTable = new int[][]
		{
				{ 0, 0, 0 },
				{ 0, 1, 1 },
				{ 0, 2, 2 },
				{ 1, 0, 1 },
				{ 1, 1, 2 },
				{ 1, 2, 0 },
				{ 2, 0, 2 },
				{ 2, 1, 0 },
				{ 2, 2, 1 } };

		Semigroup smgrp = new Semigroup(opTable);

		System.out.println(smgrp);

		IProblem problem = new SemigroupAssociativer(smgrp);

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());

			System.out.println("Associative.");
		}
		else
			System.out.println("Not associative.");
	}
}
