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
package demos.naturalnumbers;

import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import bits.BitFixer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.ProblemFork;

public class ProblemForkDemo1
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable q = BooleanVariable.getBooleanVariable("q");
		INaturalNumber N = new NaturalNumber();

		IProblem A = new NaturalNumberFixer(N, 5);
		IProblem B = new NaturalNumberFixer(N, 11);

		IProblem p1 = new ProblemFork(q, A, B);
		IProblem p2 = new BitFixer(q, true);
		IProblem problem = new Conjunction(p1, p2);

		System.out.println(problem);

		IProblemMessage s = problem.findModel(Problem.defaultSolver());

		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("q=" + q.getValue());
			System.out.println("N=" + N);
		}
		else
			System.out.println("No solution.");
	}
}
