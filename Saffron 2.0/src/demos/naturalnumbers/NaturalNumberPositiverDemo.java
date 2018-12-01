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
import naturalnumbers.NaturalNumberPositiver;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

public class NaturalNumberPositiverDemo
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumber X = new NaturalNumber("X");

		IProblem problem = new Conjunction(new NaturalNumberFixer(X, 17),
				new NaturalNumberPositiver(X));

		System.out.print(problem);

		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.print("\nX=" + X);
		}
		else
			System.out.print("\nNo solution.");
	}
}