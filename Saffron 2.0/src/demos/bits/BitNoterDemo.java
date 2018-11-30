package demos.bits;

import bits.BitNoter;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;

public class BitNoterDemo
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable x = BooleanVariable.getBooleanVariable("x");
		IBooleanVariable y = BooleanVariable.getBooleanVariable("y");

		IProblem bitNoter = new BitNoter(x, y);
		System.out.println(bitNoter);
		BooleanLiteral.interpret(bitNoter.findModel(Problem.defaultSolver()));
		System.out.println("x = " + x.getValue());
		System.out.println("y = " + y.getValue());
	}
}