/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Jan 9, 2019
 */
package demos.naturalnumbers;

import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.NaturalNumberRelativelyPrimer;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

public class NaturalNumberRelativelyPrimerDemo
{
	public static void main(String[] args) throws Exception
	{
		NaturalNumber.setLargestNaturalNumber(600);
		INaturalNumber M = new NaturalNumber();
		INaturalNumber N = new NaturalNumber();
		INaturalNumber P = new NaturalNumber();
		INaturalNumber Q = new NaturalNumber();

		IProblem problem = new Conjunction(new IProblem[]
		{ new NaturalNumberFixer(M, 35), new NaturalNumberFixer(N, 22),
				new NaturalNumberRelativelyPrimer(M, N, P, Q) });

		System.out.println(problem);

		IProblemMessage s = problem.findModel(Problem.defaultSolver());

		if (s.getStatus() == IProblemMessage.SATISFIABLE
				&& s.getLiterals().size() > 0)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.print("\nM = " + M);
			System.out.print("\tP = " + P);
			System.out.print("\nN = " + N);
			System.out.print("\tQ = " + Q);
		}
		else
			System.out.println("No solution.");
	}
}
