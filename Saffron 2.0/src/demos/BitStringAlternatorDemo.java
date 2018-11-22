package demos;

import java.util.List;

import bits.BooleanLiteral;
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
		IProblem problem = new BitStringAlternator(white);

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
