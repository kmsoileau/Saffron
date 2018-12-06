package demos.sets;

import java.util.List;

import sets.Set;
import sets.Subsetter;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.IProblem;
import bits.Problem;

public class SubsetterDemo1
{
	public static void main(String[] args) throws Exception
	{
		Set setA = new Set("Set_A");
		setA.addSupport("B");
		setA.addSupport("C");

		Set setB = new Set("Set_B");
		setB.addSupport("A");
		setB.addSupport("B");
		setB.addSupport("C");

		IProblem problem = new Conjunction(
				new SetMembershipper("B", setA),	//setA contains B
				new SetMembershipper("C", setA), 	//setA contains C
				new Subsetter(setA, setB));			//setA is a subset of setB

		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println(setA.getName()+"=" + setA);
			System.out.println(setB.getName()+"=" + setB);
		}
		else
			System.out.print("No solution.");
	}
}
