package in_development;

import bits.BooleanLiteral;
import bits.EnhancedProblem;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.ProblemDenier;

public class ProblemDenierDemo5
{
	public static void main(String[] args) throws Exception
	{
		IProblem p = EnhancedProblem.unsolvableProblem();
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
			System.out.println("Solved.");
		}
		else
			System.out.println("No solution.");
	}
}