package demos.sets;

import java.util.List;

import sets.Set;
import sets.Subsetter;
import bits.BooleanLiteral;
import bits.IBooleanLiteral;
import bits.IProblem;
import bits.Problem;

public class SubsetterDemo3
{
	public static void main(String[] args) throws Exception
	{
		Set setA = new Set();
		setA.add("A");
		setA.add("B");
		setA.add("C");

		Set setB = new Set();
		setB.add("B");
		setB.add("C");

		IProblem problem = new Subsetter(setA, setB);

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
