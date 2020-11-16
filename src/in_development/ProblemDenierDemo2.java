package in_development;

import bits.BooleanLiteral;
import bits.EnhancedProblem;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.ProblemDenier;

public class ProblemDenierDemo2
{
	public static void main(String[] args) throws Exception
	{
		IProblem p = EnhancedProblem.trivialProblem();
		System.out.println(p);

		IProblem pd = new ProblemDenier(p);
		System.out.println(pd);

		IProblem problem = pd;

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