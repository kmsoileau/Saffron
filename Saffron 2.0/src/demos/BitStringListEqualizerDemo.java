package demos;

import java.util.List;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IBooleanLiteral;
import bits.IProblem;
import bits.Problem;
import bitstringlists.BitStringList;
import bitstringlists.BitStringListEqualizer;
import bitstringlists.BitStringListFixer;
import bitstringlists.IBitStringList;
import bitstrings.BitString;

/**
 * <p>
 * Title: BitStringListEqualizerDemo
 * </p>
 * <p>
 * Description: This is a sample application showing the use of the
 * BitStringListEqualizer class.
 * </p>
 * <p>
 * Copyright (c) 2005
 * </p>
 * <p>
 * Company: Positronic Software
 * </p>
 * 
 * @author Kerry Michael Soileau
 * @version 1.0
 */
public class BitStringListEqualizerDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitStringList A = new BitStringList("A", new IBitString[]
		{ new BitString("000"), new BitString("011"), new BitString("110"),
				new BitString("010") });

		IBitStringList B = new BitStringList("B", new IBitString[]
		{ new BitString("000"), new BitString("011"), new BitString("010"),
				new BitString("011") });

		System.out.println(B);

		IProblem problem = new Conjunction(new BitStringListFixer(A),
				new BitStringListFixer(B), new BitStringListEqualizer(A, B));

		System.out.println(problem);

		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("A = " + A);
			System.out.println("B = " + B);
		}
		else
			System.out.println("No solution.");
	}
}