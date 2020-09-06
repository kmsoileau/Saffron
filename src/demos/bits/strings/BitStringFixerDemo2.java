package demos.bits.strings;

import bits.BooleanLiteral;
import bits.IBitString;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.BitStringFixer;

public class BitStringFixerDemo2
{
	public static void main(String[] args) throws Exception
	{
		IBitString target = new BitString(8);
		IProblem problem = new BitStringFixer(target, new int[]
		{ 1, 3, 5, 2 });

		System.out.print(problem);
		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println(target);

		}
		else
			System.out.println("No solution.");
	}
}