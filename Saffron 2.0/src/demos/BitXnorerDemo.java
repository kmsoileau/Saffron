package demos;

import java.util.List;

import bits.BitFixer;
import bits.BitXnorer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;

public class BitXnorerDemo
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable x = BooleanVariable.getBooleanVariable("x");
		IBooleanVariable y = BooleanVariable.getBooleanVariable("y");
		IBooleanVariable z = BooleanVariable.getBooleanVariable("z");

		IProblem problem = new Conjunction(new BitFixer(x, false),
				new BitFixer(z, true), new BitXnorer(x, y, z));
		System.out.println(problem);

		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("x= " + x.getValue());
			System.out.println("y= " + y.getValue());
			System.out.println("z= " + z.getValue());
		}
		else
			System.out.println("No solution.");
	}
}