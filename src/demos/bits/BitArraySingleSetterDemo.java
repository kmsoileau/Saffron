package demos.bits;

import bits.BitArraySingleSetter;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

public class BitArraySingleSetterDemo
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable[] array = new IBooleanVariable[]
		{ BooleanVariable.getBooleanVariable("A"), BooleanVariable.getBooleanVariable("B"),
				BooleanVariable.getBooleanVariable("C"), BooleanVariable.getBooleanVariable("D") };

		IProblem bitArraySingleSetter1 = new BitArraySingleSetter(array);
		System.out.println(bitArraySingleSetter1);

		IProblemMessage s = bitArraySingleSetter1.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			for (int i = 0; i < array.length; i++)
				System.out.println(array[i].getName() + " = " + array[i].getValue());
			BooleanLiteral.reset(s.getLiterals());
		}
		else
			System.out.println("No solution.");
	}
}
