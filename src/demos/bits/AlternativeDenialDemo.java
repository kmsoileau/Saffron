package demos.bits;

import in_development.AlternativeDenial;
import bits.BitFixer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

public class AlternativeDenialDemo
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable x1 = BooleanVariable.getBooleanVariable("x1");
		IBooleanVariable x2 = BooleanVariable.getBooleanVariable("x2");

		IProblem problem = new AlternativeDenial(new IProblem[]
		{ new BitFixer(x1, true), new BitFixer(x1, false),
				new BitFixer(x2, true), new BitFixer(x2, false) });

		System.out.println("The alternative denial was\n" + problem);

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE
				&& s.getLiterals().size() > 0)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println(x1);
			System.out.println(x2);
		}
		else
			System.out.println("There is no solution.");
	}
}