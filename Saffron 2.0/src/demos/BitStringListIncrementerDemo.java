package demos;

import java.util.List;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IBooleanLiteral;
import bits.IProblem;
import bits.Problem;
import bitstringlists.BitStringList;
import bitstringlists.BitStringListFixer;
import bitstringlists.BitStringListIncrementer;
import bitstringlists.IBitStringList;
import bitstrings.BitString;

/**
 * <p>
 * Title: BitStringListIncrementerDemo
 * </p>
 * <p>
 * Description:
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

public class BitStringListIncrementerDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitStringList firstList = new BitStringList("firstList",
				new IBitString[]
				{ new BitString("111"), new BitString("010"),
						new BitString("000") });

		IBitStringList secondList = new BitStringList("secondList",
				new IBitString[]
				{ new BitString("000"), new BitString("010"),
						new BitString("110"), new BitString("111") });

		IBitString theBitString = new BitString(3);

		IProblem problem = new Conjunction(new BitStringListFixer(firstList),
				new BitStringListFixer(secondList),
				new BitStringListIncrementer(firstList, theBitString,
						secondList));

		System.out.println(problem);

		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println(firstList);
			System.out.println(theBitString);
			System.out.println(secondList);
		}
		else
			System.out.println("No solution.");
	}
}