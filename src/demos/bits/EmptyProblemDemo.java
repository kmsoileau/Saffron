package demos.bits;

import bits.BooleanLiteral;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

public class EmptyProblemDemo
{
	public static void main(String[] args) throws Exception
	{
		IProblem problem = Problem.newProblem();

		System.out.println(problem);

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE
				&& s.getLiterals().size() > 0)
		{
			BooleanLiteral.interpret(s.getLiterals());
		}
		else
			System.out.println("No solution.");
	}
}