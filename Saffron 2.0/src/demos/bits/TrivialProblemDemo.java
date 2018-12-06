package demos.bits;

import java.util.List;

import bits.BooleanLiteral;
import bits.IProblem;
import bits.Problem;

public class TrivialProblemDemo
{
	public static void main(String[] args) throws Exception
	{
		IProblem problem = Problem.trivialProblem();

		System.out.println(problem);

		

		List<?> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
		}
		else
			System.out.println("No solution.");
	}
}