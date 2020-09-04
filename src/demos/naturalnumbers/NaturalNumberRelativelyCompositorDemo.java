package demos.naturalnumbers;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
/**
 * <p>Title: TBS</p>
 * <p>Description: TBS</p>
 * <p>Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */
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
		IProblemMessage s = problem.findModel(Problem.defaultSolver());

		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.print("\nM = " + M);
			System.out.print("\tN = " + N);
			System.out.print("\tCommon Factor Found= " + CF);
		}
		else
			System.out.println("No solution.");
	}
}