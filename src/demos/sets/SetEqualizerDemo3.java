package demos.sets;

import java.util.List;

import sets.Set;
import sets.SetEqualizer;
import bits.BooleanLiteral;
import bits.IBooleanLiteral;
import bits.IProblem;
import bits.Problem;

public class SetEqualizerDemo3
{
	public static void main(String[] args) throws Exception
	{
		Set setA = new Set();
		Set setB = new Set();

		IProblem problem = new SetEqualizer(setA, setB);

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
