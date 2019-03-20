package demos.naturalnumberlists;

import naturalnumberlists.INaturalNumberList;
import naturalnumberlists.NaturalNumberList;
import naturalnumberlists.NaturalNumberListFixer;
import naturalnumberlists.NaturalNumberListMiner;
import naturalnumbers.NaturalNumber;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

/**
 * <p>
 * Title: NaturalNumberListMinerDemo
 * </p>
 * <p>
 * Description: TBS
 * </p>
 * <p>
 * Copyright (c) 2007
 * </p>
 * <p>
 * Company: Positronic Software
 * </p>
 * 
 * @author Kerry Michael Soileau
 * @version 1.0
 */
public class NaturalNumberListMinerDemo
{
	public static void main(String[] args) throws Exception
	{
		NaturalNumber.setLength(9);
		INaturalNumberList numberList = new NaturalNumberList("numberList",
				new INaturalNumber[]
				{ new NaturalNumber(253), new NaturalNumber(101),
						new NaturalNumber(301), new NaturalNumber(211),
						new NaturalNumber(172), new NaturalNumber(353),
						new NaturalNumber(12), new NaturalNumber(87),
						new NaturalNumber(301) });
		System.out.println(numberList);

		INaturalNumber theMinValue = new NaturalNumber("minValue");

		IProblem minProb = new NaturalNumberListMiner(numberList, theMinValue);
		IProblem fixListProb = new NaturalNumberListFixer(numberList);

		IProblem problem = new Conjunction(minProb, fixListProb);
		System.out.println(problem);

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE
				&& s.getLiterals().size() > 0)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("minValue = " + theMinValue);
		}
		else
			System.out.println("No solution.");
	}
}