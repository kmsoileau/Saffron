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
import algebra.semigroups.generalized.SemigroupFixer;
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
public class SemigroupAssociativerDemo
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

		Semigroup smgrp = new Semigroup(opTable);

		System.out.println(smgrp);

		INaturalNumber X = new NaturalNumber();
		INaturalNumber Y = new NaturalNumber();
		INaturalNumber Z = new NaturalNumber();

		IProblem problem = new Conjunction(new IProblem[]
		{ new SemigroupFixer(smgrp), new NaturalNumberFixer(X, 1), new NaturalNumberFixer(Y, 3),
				new NaturalNumberFixer(Z, 1), new SemigroupAssociativer(smgrp, X, Y, Z) });

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
