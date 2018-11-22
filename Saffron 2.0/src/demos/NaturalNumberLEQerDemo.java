package demos;

import java.util.List;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.NaturalNumberLEQer;

public class NaturalNumberLEQerDemo
{
	public static void main(String[] args) throws Exception
	{
		NaturalNumber.setLargestNaturalNumber(100);

		INaturalNumber X = new NaturalNumber("X");
		INaturalNumber Y = new NaturalNumber("Y");

		IProblem problem = new Conjunction(new NaturalNumberFixer(X, 31),
				new NaturalNumberFixer(Y, 34), new NaturalNumberLEQer(X, Y));

		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println(X);
			System.out.println(Y);
			BooleanLiteral.reset(s);
		}
		else
			System.out.println("No solution.");
	}
}
