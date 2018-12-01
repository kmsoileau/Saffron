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
import naturalnumbers.NaturalNumberRightShifter;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

public class NaturalNumberRightShifterDemo
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumber X = new NaturalNumber("X");
		INaturalNumber Z = new NaturalNumber("Z");

		NaturalNumberFixer bnnfx = new NaturalNumberFixer(X, 4);

		NaturalNumberRightShifter ShiftRight1 = new NaturalNumberRightShifter(
				X, Z);

		IProblem p = new Conjunction(bnnfx, ShiftRight1);
		System.out.println(p);
		List<IBooleanLiteral> s = p.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("X= " + X);
			System.out.println("Z= " + Z);
		}
		else
			System.out.println("No solution.");
	}
}