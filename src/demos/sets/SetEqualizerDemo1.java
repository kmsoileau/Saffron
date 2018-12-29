package demos.sets;

import java.util.List;

import sets.Set;
import sets.SetEqualizer;
import bits.BitFixer;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.IProblem;
import bits.Problem;

public class SetEqualizerDemo1
{
	public static void main(String[] args) throws Exception
	{
		Set.setElementNames(new String[]{"A","B","C"});
		
		Set setA = new Set();
		

		Set setB = new Set();
		

		IProblem problem = new Conjunction(new BitFixer(setB.contains("B"),
				true), new SetEqualizer(setA, setB));

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
