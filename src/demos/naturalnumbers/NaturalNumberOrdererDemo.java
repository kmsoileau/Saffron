package demos.naturalnumbers;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.NaturalNumberOrderer;

public class NaturalNumberOrdererDemo
{
	public static void main(String[] args) throws Exception
	{
		NaturalNumber X = new NaturalNumber("X", 14L);
		NaturalNumber Y = new NaturalNumber("Y", 15L);

		NaturalNumberFixer N1 = new NaturalNumberFixer(X);
		NaturalNumberFixer N2 = new NaturalNumberFixer(Y);
		IProblem A = new NaturalNumberOrderer(X, Y);

		IProblem problem = new Conjunction(N1, N2, A);

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
