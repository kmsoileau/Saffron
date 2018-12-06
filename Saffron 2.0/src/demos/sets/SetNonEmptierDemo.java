package demos.sets;

import java.util.List;

import sets.Set;
import sets.SetNonemptier;
import bits.BooleanLiteral;
import bits.IBooleanLiteral;
import bits.IProblem;
import bits.Problem;

public class SetNonEmptierDemo
{
	public static void main(String[] args) throws Exception
	{
		Set theSet = new Set();
		theSet.add("A");
		theSet.add("B");
		theSet.add("C");

		IProblem problem = new SetNonemptier(theSet);

		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.print("theSet= " + theSet);
		}
		else
			System.out.print("No solution.");
	}
}
