package demos.sets;

import sets.Set;
import sets.SetEqualizer;
import bits.BitFixer;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

public class SetEqualizerDemo2
{
	public static void main(String[] args) throws Exception
	{
		Set.setElementNames(new String[]
		{ "A", "B", "C" });
		Set setA = new Set();

		Set setB = new Set();

		IProblem problem = new Conjunction(new BitFixer(setA.contains("B"),
				true), new SetEqualizer(setA, setB));

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE
				&& s.getLiterals().size() > 0)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("setA= " + setA);
			System.out.println("setB= " + setB);
		}
		else
			System.out.print("No solution.");
	}
}
