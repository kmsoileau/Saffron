package demos.bits.strings;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.BitStringFixer;
import bits.strings.BitStringIntersector;

public class BitStringIntersectorDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitString X = new BitString("01011010");
		IBitString Y = new BitString("00110000");

		IProblem xFix = new BitStringFixer(X);
		IProblem yFix = new BitStringFixer(Y);

		IProblem problem = new Conjunction(xFix, yFix, new BitStringIntersector(X, Y));

		System.out.println(problem);
		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("X= " + X.toBits());
			System.out.println("Y= " + Y.toBits());
		}
		else
			System.out.println("No solution.");
	}
}