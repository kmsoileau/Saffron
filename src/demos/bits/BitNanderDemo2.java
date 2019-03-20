package demos.bits;

import bits.BitNander;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;

public class BitNanderDemo2
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable x = BooleanVariable.getBooleanVariable("x");
		IBooleanVariable y = BooleanVariable.getBooleanVariable("y");
		IBooleanVariable z = BooleanVariable.getBooleanVariable("z");

		IProblem bitNander1 = new BitNander(x, y, z);
		System.out.println(bitNander1);
		BooleanLiteral.interpret(bitNander1.findModel(Problem.defaultSolver())
				.getLiterals());
		System.out.println("x = " + x.getValue());
		System.out.println("y = " + y.getValue());
		System.out.println("z = " + z.getValue());

		IProblem compressed = ((Problem) bitNander1).compress();
		System.out.println(compressed);
	}
}