package demos.bits;

import java.util.List;

import bits.BitArraySingleSetter;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.IBooleanLiteral;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;

public class BitArraySingleSetterDemo
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable[] array = new IBooleanVariable[]
		{ BooleanVariable.getBooleanVariable("A"),
				BooleanVariable.getBooleanVariable("B"),
				BooleanVariable.getBooleanVariable("C"),
				BooleanVariable.getBooleanVariable("D") };

		IProblem bitArraySingleSetter1 = new BitArraySingleSetter(array);
		System.out.println(bitArraySingleSetter1);

		List<IBooleanLiteral> s = bitArraySingleSetter1.findModel(Problem
				.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			for (int i = 0; i < array.length; i++)
				System.out.println(array[i].getName() + " = "
						+ array[i].getValue());
			BooleanLiteral.reset(s);
		}
		else
			System.out.println("No solution.");
	}
}
