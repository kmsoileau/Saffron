package demos;

import bits.BooleanVariable;
import bits.IBooleanVariable;

public class BooleanVariableDemo
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable booleanVariable1 = BooleanVariable.getBooleanVariable(
				"x", true);
		System.out.println(booleanVariable1);
		IBooleanVariable booleanVariable2 = BooleanVariable.getBooleanVariable(
				"y", false);
		System.out.println(booleanVariable2);
		IBooleanVariable booleanVariable3 = BooleanVariable.getBooleanVariable(
				"x", false);
		if (booleanVariable1 == booleanVariable3)
			System.out
					.println("booleanVariable1 is the same object as booleanVariable3.");
	}
}