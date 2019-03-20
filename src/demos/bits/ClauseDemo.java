package demos.bits;

import bits.BooleanLiteral;
import bits.Clause;
import bits.IClause;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

public class ClauseDemo
{
	public static void main(String[] args) throws Exception
	{
		IClause clause = new Clause();
		IProblem problem = new Problem();
		problem.addClause(clause);

		System.out.println(problem);

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE
				&& s.getLiterals().size() > 0)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("Satisfied by any certificate.");
		}
		else
			System.out.println("No solution.");
	}
}