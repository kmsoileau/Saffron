package demos.bitstrings;

/**
 * <p>Title: TBS</p>
 * <p>Description: TBS</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */
import java.util.List;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IBooleanLiteral;
import bits.IProblem;
import bits.Problem;
import bitstrings.BitString;
import bitstrings.BitStringDisjointer;
import bitstrings.BitStringFixer;

public class BitStringDisjointerDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitString X = new BitString("01001010");
		IBitString Y = new BitString("00110000");

		IProblem xFix = new BitStringFixer(X);
		IProblem yFix = new BitStringFixer(Y);

		IProblem problem = new Conjunction(xFix, yFix, new BitStringDisjointer(
				X, Y));

		System.out.println(problem);
		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("X= " + X.toBits());
			System.out.println("Y= " + Y.toBits());
		}
		else
			System.out.println("No solution.");
	}
}