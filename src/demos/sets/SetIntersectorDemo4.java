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
		Set setA = new Set("setA", new Object[]
		{});
		Set setB = new Set("setB", new Object[]
		{});

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
