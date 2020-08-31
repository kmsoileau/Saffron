/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Dec 24, 2018
 */
package demos.bits;

import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.ProblemInnerProduct;

public class ProblemInnerProductDemo extends Problem implements IProblem
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumber X = new NaturalNumber("X");
		INaturalNumber Y = new NaturalNumber("Y");
		IProblem[] P = new IProblem[]
		{ new NaturalNumberFixer(X, 0), new NaturalNumberFixer(X, 1),
				new NaturalNumberFixer(X, 1), new NaturalNumberFixer(X, 0),
				new NaturalNumberFixer(X, 1) };
		IProblem[] Q = new IProblem[]
		{ new NaturalNumberFixer(Y, 1), new NaturalNumberFixer(Y, 2),
				new NaturalNumberFixer(Y, 3), new NaturalNumberFixer(Y, 4),
				new NaturalNumberFixer(Y, 5) };

		IProblemMessage s = new Conjunction(new ProblemInnerProduct(P, Q),
				new NaturalNumberFixer(X, 1))
				.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("X=" + X);
			System.out.println("Y=" + Y);
		}
		else
			System.out.println("No solution.");
	}
}
