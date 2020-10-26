package demos.bits;

import bits.BitInequalityIndicator;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;

public class BitInequalityIndicatorDemo
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable x = BooleanVariable.getBooleanVariable("x");
		IBooleanVariable y = BooleanVariable.getBooleanVariable("y");
		IBooleanVariable z = BooleanVariable.getBooleanVariable("z");

		IProblem bitInequalityIndicator1 = new BitInequalityIndicator(x, y, z);
		System.out.println(bitInequalityIndicator1);

		BooleanLiteral.interpret(bitInequalityIndicator1.findModel(Problem.defaultSolver()).getLiterals());
		System.out.println("x = " + x.getValue());
		System.out.println("y = " + y.getValue());
		System.out.println("z = " + z.getValue());
	}
}
