package demos;

/**
 * <p>Title: TBS</p>
 * <p>Description: TBS</p>
 * <p>Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */
import java.util.List;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.NaturalNumberRelativelyCompositor;

public class NaturalNumberRelativelyCompositorDemo
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumber M = new NaturalNumber();
		INaturalNumber N = new NaturalNumber();
		INaturalNumber CF = new NaturalNumber();

		IProblem problem = new Conjunction(new IProblem[]
		{ new NaturalNumberFixer(M, 28), new NaturalNumberFixer(N, 21),
				new NaturalNumberRelativelyCompositor(M, N, CF) });

		System.out.println(problem);
		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());

		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.print("\nM = " + M);
			System.out.print("\tN = " + N);
			System.out.print("\tCommon Factor Found= " + CF);
		}
		else
			System.out.println("No solution.");
	}
}