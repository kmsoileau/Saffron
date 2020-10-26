package demos.bits.bugs;

import bits.BooleanLiteral;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

public class NewProblemDemo
{
	public static void main(String[] args) throws Exception
	{
		IProblem unsat = Problem.newProblem();

		System.out.println(unsat);

		IProblemMessage s = unsat.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
		}
		else
			System.out.println("No solution.");
	}
}