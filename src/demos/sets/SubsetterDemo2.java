package demos.sets;

import bits.BooleanLiteral;
import bits.IProblemMessage;
import bits.Problem;
import sets.Set;
import sets.Subsetter;

public class SubsetterDemo2
{
	public static void main(String[] args) throws Exception
	{
		Set setA = new Set("Set A");
		Set setB = new Set("Set B");

		IProblemMessage s = new Subsetter(setA, setB).findModel(Problem
				.defaultSolver());
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
