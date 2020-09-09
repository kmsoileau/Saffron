package demos.bits;

import java.util.List;

import bits.BitFixer;
import bits.BitShefferStroker;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;

public class BitShefferStrokerDemo
{
	public static void main(String[] args) throws Exception
	{
		// Create the BooleanVariables:
		IBooleanVariable x = BooleanVariable.getBooleanVariable("x");
		IBooleanVariable y = BooleanVariable.getBooleanVariable("y");

		// Construct the object that implements the constraint x & y = z :
		IProblem bitShefferStroker1 = new BitShefferStroker(x, y);
		System.out.println(bitShefferStroker1);

		IProblem bfx1 = new BitFixer(x, true);
		IProblem bfy1 = new BitFixer(y, true);

		// Combine the constraints into a Problem object :
		IProblem p1 = new Conjunction(bitShefferStroker1, bfx1, bfy1);
		System.out.println(p1);

		// Find a solution to the Problem object :
		List<IBooleanLiteral> v1 = p1.findModel(Problem.defaultSolver()).getLiterals();
		System.out.println(v1);
	}
}