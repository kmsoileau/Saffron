package demos.bits;

import bits.BitFixer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.FredkinGater;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

public class FredkinGaterDemo
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

		IBooleanVariable A = BooleanVariable.getBooleanVariable("A");
		IBooleanVariable B = BooleanVariable.getBooleanVariable("B");
		IBooleanVariable C = BooleanVariable.getBooleanVariable("C");
		IBooleanVariable P = BooleanVariable.getBooleanVariable("P");
		IBooleanVariable Q = BooleanVariable.getBooleanVariable("Q");
		IBooleanVariable R = BooleanVariable.getBooleanVariable("R");

		/**
		 * Create problems which constrain the values of these Saffron objects:
		 */

		IProblem fredkinGater1 = new FredkinGater(A, B, C, P, Q, R);
		System.out.println(fredkinGater1);

		IProblem a1 = new BitFixer(A, false);
		IProblem b1 = new BitFixer(B, true);
		IProblem c1 = new BitFixer(C, false);

		/**
		 * Create the IProblem of satisfying all of these constraining problems:
		 */

		IProblem p1 = new Conjunction(fredkinGater1, a1, b1, c1);

		/**
		 * Solve the IProblem:
		 */

		IProblemMessage v1 = p1.findModel(Problem.defaultSolver());
		System.out.println(v1);
		BooleanLiteral.interpret(v1.getLiterals());

		System.out.println("----!------------");
		System.out.println("A = " + A.getValue());
		System.out.println("B = " + B.getValue());
		System.out.println("C = " + C.getValue());
		System.out.println("P = " + P.getValue());
		System.out.println("Q = " + Q.getValue());
		System.out.println("R = " + R.getValue());

		IProblem rp = p1.resolve(v1.getLiterals());
		System.out.println("::::::::::");
		System.out.println(rp);
	}
}