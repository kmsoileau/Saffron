package demos.bits;

import java.util.List;

import bits.BitArraySingleClearer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.IBooleanLiteral;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;

public class BitArraySingleClearerDemo
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable[] array = new IBooleanVariable[]
		{ BooleanVariable.getBooleanVariable("A"),
				BooleanVariable.getBooleanVariable("B"),
				BooleanVariable.getBooleanVariable("C"),
				BooleanVariable.getBooleanVariable("D") };

		IProblem bitArraySingleClearer1 = new BitArraySingleClearer(array);
		System.out.println(bitArraySingleClearer1);

		List<IBooleanLiteral> s = bitArraySingleClearer1.findModel(Problem
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
