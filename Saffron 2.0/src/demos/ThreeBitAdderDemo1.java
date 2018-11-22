package demos;

import java.util.List;

import bits.BitFixer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;
import bits.ThreeBitAdder;

public class ThreeBitAdderDemo1
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable w = BooleanVariable.getBooleanVariable("w");
		IBooleanVariable x = BooleanVariable.getBooleanVariable("x");
		IBooleanVariable y = BooleanVariable.getBooleanVariable("y");
		IBooleanVariable z = BooleanVariable.getBooleanVariable("z");
		IBooleanVariable c = BooleanVariable.getBooleanVariable("c");

		IProblem threeBitAdder1 = new ThreeBitAdder(w, x, y, z, c)
				.and(new BitFixer(w, false)).and(new BitFixer(x, true))
				.and(new BitFixer(y, true));
		threeBitAdder1.sort();
		System.out.println(threeBitAdder1);

		List<?> s = threeBitAdder1.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println(w);
			System.out.println(x);
			System.out.println(y);
			System.out.println(z);
			System.out.println(c);
		}
		else
			System.out.println("There is no solution.");
	}
}