package demos.naturalnumbers;

/**
 * <p>Title: TBS</p>
 * <p>Description: TBS</p>
 * <p>Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */
import java.util.List;

import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.NaturalNumberRelativelyPrimer;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

public class NaturalNumberRelativelyPrimerDemo
{
	public static void main(String[] args) throws Exception
	{
		NaturalNumber.setLength(6);
		INaturalNumber M = new NaturalNumber();
		INaturalNumber N = new NaturalNumber();

		IProblem problem = new Conjunction(new IProblem[]
		{ new NaturalNumberFixer(M, 21), new NaturalNumberFixer(N, 35),
				new NaturalNumberRelativelyPrimer(M, N) });

		System.out.println(problem);

		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());

		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.print("\nM = " + M);
			System.out.print("\tN = " + N);
		}
		else
			System.out.println("No solution.");
	}
}