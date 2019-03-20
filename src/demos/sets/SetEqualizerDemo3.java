package demos.sets;

import sets.Set;
import sets.SetEqualizer;
import bits.BooleanLiteral;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

public class SetEqualizerDemo3
{
	public static void main(String[] args) throws Exception
	{
		Set setA = new Set();
		Set setB = new Set();

		IProblem problem = new SetEqualizer(setA, setB);

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
