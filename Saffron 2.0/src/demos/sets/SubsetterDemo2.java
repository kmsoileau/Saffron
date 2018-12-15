package demos.sets;

import java.util.List;

import sets.Set;
import sets.Subsetter;
import bits.BooleanLiteral;
import bits.IBooleanLiteral;
import bits.Problem;

public class SubsetterDemo2
{
	public static void main(String[] args) throws Exception
	{
		Set setA = new Set("Set A");
		Set setB = new Set("Set B");

		List<IBooleanLiteral> s = new Subsetter(setA, setB).findModel(Problem
				.defaultSolver());
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
