package demos.bits;

import bits.BitFixer;
import bits.BitUnequalizer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

public class BitUnequalizerDemo
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable x = BooleanVariable.getBooleanVariable("x");
		IBooleanVariable y = BooleanVariable.getBooleanVariable("y");

		IProblem fixx = new BitFixer(x, true);
		IProblem fixy = new BitFixer(y, false);
		IProblem bitUnequalizer1 = new BitUnequalizer(x, y);

		IProblem problem = new Conjunction(fixx, fixy, bitUnequalizer1);
		System.out.println(problem);
		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE
				&& s.getLiterals().size() > 0)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("x= " + x.getValue());
			System.out.println("y= " + y.getValue());
		}
		else
			System.out.println("No solution.");
	}
}