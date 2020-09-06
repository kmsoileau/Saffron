package demos.bits.strings;

import bits.BooleanLiteral;
import bits.IBitString;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.BitStringZerothBitFixer;

public class BitStringZerothBitFixerDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitString b = new BitString(new boolean[20]);
		IProblem problem = new BitStringZerothBitFixer(b, false);

		System.out.println(problem);
		IProblemMessage s = problem.findModel(Problem.defaultSolver());

		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println(b);
		}
		else
			System.out.println("No solution.");
	}
}