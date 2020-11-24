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
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberCompositor;
import naturalnumbers.NaturalNumberFixer;

public class NaturalNumberCompositorDemo
{
	public static void main(String[] args) throws Exception
	{
		NaturalNumber.setLargestNaturalNumber(10000);
		INaturalNumber Z = new NaturalNumber("Z");
		INaturalNumber X = new NaturalNumber("X");
		INaturalNumber Y = new NaturalNumber("Y");

		for (int number = 1000; number <= 10000; number++)
		{
			IProblem p = new Conjunction(new NaturalNumberFixer(Z, number), new NaturalNumberCompositor(Z, X, Y));
			IProblemMessage s = p.findModel(Problem.defaultSolver());
			if (s.getStatus() == IProblemMessage.SATISFIABLE)
			{
				BooleanLiteral.interpret(s.getLiterals());
				System.out.println(Z + " is composite: "+X + " * " + Y + " = " + Z);
				//System.out.println();
			}
			//else
			//	System.out.println("No solution.");
		}
	}
}