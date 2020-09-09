package demos.algebra.groups.generalized;

import algebra.groups.generalized.Grouper;
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
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 8, 2019
 */

/**
 * 
 *
 */
public class KleinVierGruppeDemo2
{
	public static void main(String[] args) throws Exception
	{
		// Klein 4-group
		INaturalNumber E1 = new NaturalNumber("E1");
		INaturalNumber E2 = new NaturalNumber("E2");
		INaturalNumber E3 = new NaturalNumber("E3");

		Grouper groupProblem = new Grouper(new int[][]
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
				{ 3, 3, 0 } }, E1, E2, E3);

		IProblem problem = new Conjunction(groupProblem, new NaturalNumberFixer(E1, 1), new NaturalNumberFixer(E3, 2));

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("E1 = " + E1);
			System.out.println("E2 = " + E2);
			System.out.println("E3 = " + E3);
		}
		else
			System.out.println("No solution.");
	}
}
