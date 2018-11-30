package demos.bitstrings;

import java.util.List;

import bits.BitFixer;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IBooleanLiteral;
import bits.IProblem;
import bits.Problem;
import bitstrings.BitString;
import bitstrings.BitStringAlternator;

public class BitStringAlternatorDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitString white = new BitString(new boolean[20]);
		IProblem bsa = new BitStringAlternator(white);
		IProblem fixBit= new BitFixer(white.getBooleanVariable(2),false);

		IProblem problem = new Conjunction(bsa,fixBit);
		System.out.println(problem);
		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());

		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println(white);
		}
		else
			System.out.println("No solution.");
	}

}
