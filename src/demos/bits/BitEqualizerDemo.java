package demos.bits;

import java.util.List;

import bits.BitEqualizer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.IBooleanLiteral;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;

public class BitEqualizerDemo
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable x = BooleanVariable.getBooleanVariable("x");
		IBooleanVariable y = BooleanVariable.getBooleanVariable("y");

		IProblem bitEqualizer1 = new BitEqualizer(x, y);
		System.out.println(bitEqualizer1);

		System.out.println("DIMACS:\n" + bitEqualizer1.toDIMACS());

		List<IBooleanLiteral> s = bitEqualizer1.findModel(Problem
				.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("x= " + x.getValue());
			System.out.println("y= " + y.getValue());
		}
		else
			System.out.println("No solution.");
	}
}