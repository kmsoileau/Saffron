package demos.bits.strings.lists;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.lists.BitStringList;
import bits.strings.lists.BitStringListFixer;
import bits.strings.lists.BitStringListIncrementer;
import bits.strings.lists.IBitStringList;

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

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println(firstList);
			System.out.println(theBitString);
			System.out.println(secondList);
		}
		else
			System.out.println("No solution.");
	}
}