package demos.sets;

import java.util.List;

import sets.Set;
import sets.Subsetter;
import bits.BooleanLiteral;
import bits.IBooleanLiteral;
import bits.IProblem;
import bits.Problem;

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
