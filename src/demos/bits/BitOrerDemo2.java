package demos.bits;

import bits.BitOrer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;

public class BitOrerDemo2
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable x = BooleanVariable.getBooleanVariable("x");
		IBooleanVariable y = BooleanVariable.getBooleanVariable("y");
		IBooleanVariable z = BooleanVariable.getBooleanVariable("z");

		IProblem bitOrer1 = new BitOrer(x, y, z);
		System.out.println(bitOrer1);
		BooleanLiteral.interpret(bitOrer1.findModel(Problem.defaultSolver()).getLiterals());
		System.out.println("x = " + x.getValue());
		System.out.println("y = " + y.getValue());
		System.out.println("z = " + z.getValue());

		System.out.println(bitOrer1);
	}
}