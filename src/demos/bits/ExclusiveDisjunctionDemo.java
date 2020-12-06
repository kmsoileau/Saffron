package demos.bits;

import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Clause;
import bits.EnhancedProblem;
import bits.ExclusiveDisjunction;
import bits.IBooleanVariable;
import bits.IClause;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

public class ExclusiveDisjunctionDemo
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

		IBooleanVariable[] bva = new IBooleanVariable[3];
		bva[0] = BooleanVariable.getBooleanVariable("x_1");
		bva[1] = BooleanVariable.getBooleanVariable("x_2");
		bva[2] = BooleanVariable.getBooleanVariable("x_3");

		/**
		 * Create problems which constrain the values of these Saffron objects:
		 */

		IProblem p1 = new Problem(new IClause[]
		{ new Clause().orNot(bva[0]).orNot(bva[1]).orNot(bva[2]) });
		System.out.println(p1);

		IProblem p2 = new Problem(new IClause[]
		{ new Clause().or(bva[0]).orNot(bva[1]).or(bva[2]), new Clause().orNot(bva[0]).orNot(bva[1]).orNot(bva[2]) });
		System.out.println(p2);

		/**
		 * Create the IProblem of satisfying all of these constraining problems:
		 */

		IProblem p3 = new ExclusiveDisjunction(p1, p2, EnhancedProblem.unsolvableProblem());

		/**
		 * Solve the IProblem:
		 */

		IProblemMessage s = p3.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println(bva[0]);
			System.out.println(bva[1]);
			System.out.println(bva[2]);
			BooleanLiteral.reset(s.getLiterals());
		}
		else
			System.out.println("No solution.");
	}
}