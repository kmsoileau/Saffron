package demos.bits.strings;

import bits.BitFixer;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.BitStringAlternator;

public class BitStringAlternatorDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitString white = new BitString(new boolean[20]);
		IProblem bsa = new BitStringAlternator(white);
		IProblem fixBit = new BitFixer(white.getBooleanVariable(2), false);

		IProblem problem = new Conjunction(bsa, fixBit);
		System.out.println(problem);
		IProblemMessage s = problem.findModel(Problem.defaultSolver());

		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println(white);
		}
		else
			System.out.println("No solution.");
	}

}
