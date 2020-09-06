package demos.integers;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import integers.Integer;
import integers.IntegerFixer;
import integers.IntegerOrderer;

public class IntegerOrdererDemo
{
	public static void main(String[] args) throws Exception
	{
		Integer X = new Integer("X", 14L);
		Integer Y = new Integer("Y", 15L);

		IntegerFixer N1 = new IntegerFixer(X);
		IntegerFixer N2 = new IntegerFixer(Y);
		IProblem A = new IntegerOrderer(X, Y);

		IProblem problem = new Conjunction(N1, N2, A);
		problem.sort();
		System.out.println(problem);
		// List s=PartialSolution.solveList(problem);
		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("X= " + X);
			System.out.println("Y= " + Y);
		}
		else
			System.out.println("No solution.");
	}
}
