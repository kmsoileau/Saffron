package demos.bits;

import java.util.ArrayList;

import bits.BooleanLiteral;
import bits.Clause;
import bits.IClause;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

public class ClauseDemo
{
	public static void main(String[] args) throws Exception
	{
		/**
		 * Set Java variables:
		 */

		/**
		 * Set globals:
		 */

		/**
		 * Create Saffron objects and arrays:
		 */

		IClause clause = new Clause();
		ArrayList<IClause> ret = new ArrayList<IClause>();
		ret.add(clause);

		/**
		 * Create problems which constrain the values of these Saffron objects:
		 */

		/**
		 * Create the IProblem of satisfying all of these constraining problems:
		 */

		IProblem problem = new Problem(ret.toArray(new IClause[0]));

		/**
		 * Solve the IProblem:
		 */

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("Satisfied by any certificate.");
		}
		else
			System.out.println("No solution.");

	}
}