/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Dec 25, 2018
 */
package demos.bits;

import bits.BitFixer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.ProblemIndexedConjunction;

public class ProblemIndexedConjunctionDemo
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable x = BooleanVariable.getBooleanVariable();
		IProblem[] P = new IProblem[]
		{ new BitFixer(x, true) };
		IProblem[] Q = new IProblem[]
		{ Problem.unsolvableProblem() };

		IProblem pic = new ProblemIndexedConjunction(P, Q);
		IProblem problem = new Conjunction(new BitFixer(x, false), pic);
		System.out.println(problem);

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE
				&& s.getLiterals().size() > 0)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("x=" + x.getValue());
		}
		else
			System.out.println("No solution.");
	}
}
