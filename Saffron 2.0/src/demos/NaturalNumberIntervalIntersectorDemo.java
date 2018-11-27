package demos;

import java.util.List;

import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.NaturalNumberIntervalIntersector;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

public class NaturalNumberIntervalIntersectorDemo
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumber A = new NaturalNumber("A");
		INaturalNumber B = new NaturalNumber("B");
		INaturalNumber C = new NaturalNumber("C");
		INaturalNumber D = new NaturalNumber("D");

		IProblem[] p = new IProblem[]
		{ new NaturalNumberFixer(A, 3), new NaturalNumberFixer(B, 4),
				new NaturalNumberFixer(C, 2), new NaturalNumberFixer(D, 7),
				new NaturalNumberIntervalIntersector(A, B, C, D) };

		IProblem problem = new Conjunction(p);

		System.out.println(problem);
		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("[A,B] = [" + A + "," + B + "]");
			System.out.println("[C,D] = [" + C + "," + D + "]");
		}
		else
			System.out.println("No solution.");
	}
}
