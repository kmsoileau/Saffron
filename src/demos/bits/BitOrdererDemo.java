package demos.bits;

import bits.BitFixer;
import bits.BitOrderer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

public class BitOrdererDemo
{
	public static void main(String[] args) throws Exception
	{
		// Create the BooleanVariables:
		IBooleanVariable x = BooleanVariable.getBooleanVariable("x");
		IBooleanVariable y = BooleanVariable.getBooleanVariable("y");

		// Construct the object that implements the constraint x <= y :
		IProblem bitOrderer1 = new BitOrderer(x, y);
		System.out.println(bitOrderer1);

		// Constrain the values of x and y :
		IProblem bfx1 = new BitFixer(x, false);
		IProblem bfy1 = new BitFixer(y, false);

		// Combine the constraints into a Problem object :
		IProblem p1 = new Conjunction(bitOrderer1, bfx1, bfy1);
		System.out.println(p1);
		// Find a solution to the Problem object :
		IProblemMessage s = p1.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE
				&& s.getLiterals().size() > 0)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("x = " + x.getValue());
			System.out.println("y = " + y.getValue());
		}
		else
			System.out.println("No solution.");
	}
}