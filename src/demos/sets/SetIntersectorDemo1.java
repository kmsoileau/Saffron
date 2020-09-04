package demos.sets;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import sets.Set;
import sets.SetMembershipper;
import sets.SetNonMembershipper;
import sets.Subsetter;

public class SetIntersectorDemo1
{
	public static void main(String[] args) throws Exception
	{
		Set.setElementNames(new String[]
		{ "A", "B", "C", "D", "E" });

		Set setA = new Set("setA");
		Set setB = new Set("setB");

		IProblem problem = new Conjunction(new IProblem[]
		{ new SetMembershipper("B", setA), // setA contains B
				new SetMembershipper("C", setA), // setA contains C
				new SetMembershipper("D", setA), // setA contains D
				new SetMembershipper("C", setB), // setB contains C
				new SetMembershipper("D", setB), // setB contains D
				new SetNonMembershipper("E", setB), // setB doesn't contains E
				new Subsetter(setA, setB) }); // setA is a subset of setB

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
