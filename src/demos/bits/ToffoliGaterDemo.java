package demos.bits;

import bits.BitFixer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.ToffoliGater;

public class ToffoliGaterDemo
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

		// Create the BooleanVariables:
		IBooleanVariable A = BooleanVariable.getBooleanVariable("A");
		IBooleanVariable B = BooleanVariable.getBooleanVariable("B");
		IBooleanVariable C = BooleanVariable.getBooleanVariable("C");
		IBooleanVariable P = BooleanVariable.getBooleanVariable("P");
		IBooleanVariable Q = BooleanVariable.getBooleanVariable("Q");
		IBooleanVariable R = BooleanVariable.getBooleanVariable("R");

		/**
		 * Create problems which constrain the values of these Saffron objects:
		 */

		// Construct the object that implements the constraint x & y = z :
		IProblem toffoliGater1 = new ToffoliGater(A, B, C, P, Q, R);
		System.out.println(toffoliGater1);

		// Constrain the values of A, B and C:
		IProblem a1 = new BitFixer(A, true);
		IProblem b1 = new BitFixer(B, true);
		IProblem c1 = new BitFixer(C, false);

		/**
		 * Create the IProblem of satisfying all of these constraining problems:
		 */

		// Combine the constraints into a Problem object :
		IProblem p1 = new Conjunction(toffoliGater1, a1, b1, c1);
		System.out.println(p1);

		/**
		 * Solve the IProblem:
		 */

		// Find a solution to the Problem object :
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
	}
}