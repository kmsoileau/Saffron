package demos.bitstrings;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bitstrings.BitString;
import bitstrings.BitStringExchanger;
import bitstrings.BitStringFixer;

public class BitStringExchangerDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitString xBefore = new BitString("01011");
		IBitString yBefore = new BitString("11010");
		IBitString xAfter = new BitString(5);
		IBitString yAfter = new BitString(5);

		IProblem p = new Conjunction(new IProblem[]
		{ new BitStringFixer(xBefore), new BitStringFixer(yBefore),
				new BitStringExchanger(xBefore, yBefore, xAfter, yAfter) });

		System.out.println(p);

		IProblemMessage s = p.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE
				&& s.getLiterals().size() > 0)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("xBefore=" + xBefore.toBits());
			System.out.println("yBefore=" + yBefore.toBits());
			System.out.println("xAfter=" + xAfter.toBits());
			System.out.println("yAfter=" + yAfter.toBits());
		}
		else
			System.out.println("No solution.");
	}
}
