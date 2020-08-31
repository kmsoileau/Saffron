package demos.sets;

import sets.Set;
import sets.SetNonemptier;
import bits.BooleanLiteral;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

public class SetNonEmptierDemo2
{
	public static void main(String[] args) throws Exception
	{
		Set theSet = new Set();

		IProblem problem = new SetNonemptier(theSet);

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.print("theSet= " + theSet);
		}
		else
			System.out.print("No solution.");
	}
}
