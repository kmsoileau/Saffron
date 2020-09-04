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

import bits.BitFixer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.NaturalNumber;

public class ProblemForkDemo2
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable b = BooleanVariable.getBooleanVariable("b");
		INaturalNumber N = new NaturalNumber();

		// IProblem A = new NaturalNumberFixer(N, 5);
		IProblem A = Problem.unsolvableProblem();
		IProblem B = Problem.trivialProblem();

		// (!b | P) & (b | Q)
		IProblem p1 = new Disjunction(new BitFixer(b, false), A);
		IProblem p2 = new Disjunction(new BitFixer(b, true), B);
		IProblem problem = new Conjunction(new BitFixer(b, true),
				new Conjunction(p1, p2));

		System.out.println(problem);

		IProblemMessage s = problem.findModel(Problem.defaultSolver());

		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("b=" + b.getValue());
			System.out.println("N=" + N);
		}
		else
			System.out.println("No solution.");
	}
}
