package demos;

import java.util.List;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.NaturalNumberOrderer2;

public class NaturalNumberOrderer2Demo
{
	public static void main(String[] args) throws Exception
	{
		NaturalNumber X = new NaturalNumber("X", 5L);
		NaturalNumber Y = new NaturalNumber("Y", 4L);

		IProblem problem = new Conjunction(new NaturalNumberFixer(X),
				new NaturalNumberFixer(Y), new NaturalNumberOrderer2(X, Y));

		problem.sort();
		System.out.println(problem);
		List<?> s = problem.findModel(Problem.defaultSolver());
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
