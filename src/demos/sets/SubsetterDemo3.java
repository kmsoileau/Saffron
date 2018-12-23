package demos.sets;

import java.util.List;

import sets.Set;
import sets.SetMembershipper;
import sets.Subsetter;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.IProblem;
import bits.Problem;

public class SubsetterDemo3
{
	public static void main(String[] args) throws Exception
	{
		Set setA = new Set();
		setA.addSupport("A");
		setA.addSupport("B");
		setA.addSupport("C");

		Set setB = new Set();
		setB.addSupport("B");
		setB.addSupport("C");

		IProblem problem = new Conjunction(new SetMembershipper("B", setA),
				new SetMembershipper("C", setA),
				new SetMembershipper("B", setB), new Subsetter(setA, setB));

		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("setA= " + setA);
			System.out.println("setB= " + setB);
		}
		else
			System.out.print("No solution.");
	}
}
