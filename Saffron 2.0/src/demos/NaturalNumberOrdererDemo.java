package demos;

import java.util.List;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.IProblem;
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
		problem.sort();
		System.out.println(problem);
		// List s=PartialSolution.solveList(problem);
		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("X= " + X);
			System.out.println("Y= " + Y);
		}
		else
			System.out.println("No solution.");

	}
}
