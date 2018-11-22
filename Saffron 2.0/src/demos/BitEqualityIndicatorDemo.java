package demos;

import bits.BitEqualityIndicator;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;

public class BitEqualityIndicatorDemo
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable x = BooleanVariable.getBooleanVariable("x");
		IBooleanVariable y = BooleanVariable.getBooleanVariable("y");
		IBooleanVariable z = BooleanVariable.getBooleanVariable("z");

		IProblem bitEqualityIndicator1 = new BitEqualityIndicator(x, y, z);

		System.out.println(bitEqualityIndicator1);

		BooleanLiteral.interpret(bitEqualityIndicator1.findModel(Problem
				.defaultSolver()));
		System.out.println("x = " + x.getValue());
		System.out.println("y = " + y.getValue());
		System.out.println("z = " + z.getValue());
	}
}
