package demos.sets;

import bits.BooleanLiteral;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import sets.Set;
import sets.Subsetter;

public class SetIntersectorDemo4
{
	public static void main(String[] args) throws Exception
	{
		Set.setElementNames(new String[]
		{ "A", "B", "C", "D" });

		Set setA = new Set("setA");
		Set setB = new Set("setB");

		IProblem problem = new Subsetter(setB, setA); // setA is a subset of
														// setB

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println(setA.getName() + "=" + setA);
			System.out.println(setB.getName() + "=" + setB);
		}
		else
			System.out.print("No solution.");
	}
}
