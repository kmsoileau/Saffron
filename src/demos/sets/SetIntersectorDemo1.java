package demos.sets;

import java.util.List;

import sets.Set;
import sets.SetMembershipper;
import sets.SetNonMembershipper;
import sets.Subsetter;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.IProblem;
import bits.Problem;

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

		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println(setA.getName() + "=" + setA);
			System.out.println(setB.getName() + "=" + setB);
		}
		else
			System.out.print("No solution.");
	}
}
