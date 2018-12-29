package demos.bits;

import java.util.List;

import bits.BooleanLiteral;
import bits.Clause;
import bits.IBooleanLiteral;
import bits.IClause;
import bits.IProblem;
import bits.Problem;
import bits.ProblemDenier;

public class ClauseDemo
{
	public static void main(String[] args) throws Exception
	{
		IClause clause = new Clause();
		IProblem problem0 = new Problem();
		problem0.addClause(clause);
		System.out.println(problem0);

		IProblem problem = new ProblemDenier(problem0);

		System.out.println(problem);

		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("Satisfied by any certificate.");
		}
		else
			System.out.println("No solution.");
	}
}