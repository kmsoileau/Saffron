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
import naturalnumbers.NaturalNumberRightShifter;

public class NaturalNumberRightShifterDemo
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumber X = new NaturalNumber("X");
		INaturalNumber Z = new NaturalNumber("Z");

		NaturalNumberFixer bnnfx = new NaturalNumberFixer(X, 4);

		NaturalNumberRightShifter ShiftRight1 = new NaturalNumberRightShifter(X, Z);

		IProblem p = new Conjunction(bnnfx, ShiftRight1);
		System.out.println(p);
		IProblemMessage s = p.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("X= " + X);
			System.out.println("Z= " + Z);
		}
		else
			System.out.println("No solution.");
	}
}