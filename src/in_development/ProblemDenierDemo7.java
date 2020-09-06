package in_development;

import bits.BitFixer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.ProblemDenier;

public class ProblemDenierDemo7
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable b = BooleanVariable.getBooleanVariable("b", true);
		IProblem p = new BitFixer(b, true);
		System.out.println(p);

		IProblem p2 = new ProblemDenier(p);
		System.out.println(p2);

		IProblem p3 = new ProblemDenier(p2);
		System.out.println(p3);

		IProblem problem = p3;

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("b=" + b.getValue());
		}
		else
			System.out.println("No solution.");
	}
}