package demos.bits;

import bits.BitFixer;
import bits.BitXorer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

public class BitXorerDemo
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable x = BooleanVariable.getBooleanVariable("x");
		IBooleanVariable y = BooleanVariable.getBooleanVariable("y");
		IBooleanVariable z = BooleanVariable.getBooleanVariable("z");

		IProblem fixx = new BitFixer(x, false);
		IProblem fixz = new BitFixer(z, true);
		IProblem bitXorer1 = new BitXorer(x, y, z);

		IProblem problem = new Conjunction(fixx, fixz, bitXorer1);
		System.out.println(bitXorer1);

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE
				&& s.getLiterals().size() > 0)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("x= " + x.getValue());
			System.out.println("y= " + y.getValue());
			System.out.println("z= " + z.getValue());
		}
		else
			System.out.println("No solution.");
	}
}