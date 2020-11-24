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
		// Create the BooleanVariables:
		IBooleanVariable A = BooleanVariable.getBooleanVariable("A");
		IBooleanVariable B = BooleanVariable.getBooleanVariable("B");
		IBooleanVariable C = BooleanVariable.getBooleanVariable("C");
		IBooleanVariable P = BooleanVariable.getBooleanVariable("P");
		IBooleanVariable Q = BooleanVariable.getBooleanVariable("Q");
		IBooleanVariable R = BooleanVariable.getBooleanVariable("R");

		// Construct the object that implements the constraint x & y = z :
		IProblem fredkinGater1 = new FredkinGater(A, B, C, P, Q, R);
		System.out.println(fredkinGater1);

		// Constrain the values of A, B and C:
		IProblem a1 = new BitFixer(A, false);
		IProblem b1 = new BitFixer(B, true);
		IProblem c1 = new BitFixer(C, false);

		// Combine the constraints into a Problem object :
		IProblem p1 = new Conjunction(fredkinGater1, a1, b1, c1);
		System.out.println(p1);

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

		IProblem rp = ((Problem) p1).resolve(v1.getLiterals());
		System.out.println("::::::::::");
		System.out.println(rp);
	}
}