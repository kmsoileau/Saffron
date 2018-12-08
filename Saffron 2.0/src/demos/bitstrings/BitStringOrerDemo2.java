package demos.bitstrings;

/**
 * <p>Title: TBS</p>
 * <p>Description: TBS</p>
 * <p>Copyright (c) 2005</p>
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
import bitstrings.BitStringFixer;
import bitstrings.BitStringOrer;

public class BitStringOrerDemo2
{
	public static void main(String[] args) throws Exception
	{
		IBitString[] X = new IBitString[]
		{ 
				new BitString("01001010"), 
				new BitString("10101010"),
				new BitString("00001000"), 
				new BitString("00100000") };

		IBitString Z = new BitString(8);

		IProblem p1 = new BitStringOrer(X, Z);
		IProblem p2 = new BitStringFixer(X);

		IProblem problem = new Conjunction(p1, p2);
		System.out.println(problem);
		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			for(int i=0;i<X.length;i++)
				System.out.println("X["+i+"] = " + X[i]);
			System.out.println("Z= " + Z);
		}
		else
			System.out.println("No solution.");
	}
}