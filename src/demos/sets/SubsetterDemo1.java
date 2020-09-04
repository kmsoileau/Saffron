package demos.sets;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import sets.Set;
import sets.SetMembershipper;
import sets.Subsetter;

public class SubsetterDemo1
{
	public static void main(String[] args) throws Exception
	{
		Set.setElementNames(new String[]
		{ "A", "B", "C" });

		Set setA = new Set("Set_A");
		Set setB = new Set("Set_B");

		IProblem problem = new Conjunction(new SetMembershipper("B", setA),
				new SetMembershipper("C", setA), // setA contains C
				new Subsetter(setA, setB)); // setA is a subset of setB

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
