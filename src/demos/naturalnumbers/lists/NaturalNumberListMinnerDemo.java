package demos.naturalnumbers.lists;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.NaturalNumber;
import naturalnumbers.lists.INaturalNumberList;
import naturalnumbers.lists.NaturalNumberList;
import naturalnumbers.lists.NaturalNumberListFixer;
import naturalnumbers.lists.NaturalNumberListMinner;

/**
 * <p>
 * Title: NaturalNumberListMinnerDemo
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
public class NaturalNumberListMinnerDemo
{
	public static void main(String[] args) throws Exception
	{
		NaturalNumber.setLargestNaturalNumber(10);

		INaturalNumberList d = new NaturalNumberList(new INaturalNumber[]
		{ new NaturalNumber(2), new NaturalNumber(1), new NaturalNumber(3),
				new NaturalNumber(0), new NaturalNumber(1),
				new NaturalNumber(5), new NaturalNumber(1),
				new NaturalNumber(2), new NaturalNumber(3) });

		INaturalNumber minIndex = new NaturalNumber();
		INaturalNumber minEntry = new NaturalNumber();

		IProblemMessage s = new Conjunction(new NaturalNumberListFixer(d),
				new NaturalNumberListMinner(d, minIndex, minEntry))
				.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("minIndex = " + minIndex);
			System.out.println("minEntry = " + minEntry);
		}
		else
			System.out.println("No solution.");
	}
}