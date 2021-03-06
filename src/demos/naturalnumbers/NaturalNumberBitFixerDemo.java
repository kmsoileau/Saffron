package demos.naturalnumbers;

import bits.BooleanLiteral;
import bits.INaturalNumber;
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
import naturalnumbers.NaturalNumberBitFixer;

public class NaturalNumberBitFixerDemo
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumber X = new NaturalNumber("X");
		NaturalNumberBitFixer nnbf = new NaturalNumberBitFixer(X, 3, true);
		IProblemMessage s = nnbf.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("X= " + X);
		}
		else
			System.out.println("No solution.");
	}
}