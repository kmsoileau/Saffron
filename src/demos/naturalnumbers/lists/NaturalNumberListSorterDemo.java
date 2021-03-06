/*
 * NaturalNumberListSorterDemo.java	1.0 06/02/04
 *
 * Copyright 2006 Positronic Software.
 *
 *
 */
/**
 * <p>Title: NaturalNumberListSorterDemo</p>
 * <p>Description: TBS</p>
 * <p>Copyright (c) 2006</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */

///SUSPECT CODE

package demos.naturalnumbers.lists;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.lists.INaturalNumberList;
import naturalnumbers.lists.NaturalNumberList;
import naturalnumbers.lists.NaturalNumberListFixer;
import naturalnumbers.lists.NaturalNumberListSorter;

public class NaturalNumberListSorterDemo
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumberList s1 = new NaturalNumberList("x", new long[]
		{ 2, 11, 3, 4, 5, 1, 13, 8 });
		System.out.println("s1.getName() = " + s1.getName());

		for (int i = 0; i < s1.size(); i++)
			System.out.println("s1.getNaturalNumber(" + i + ") = " + s1.getNaturalNumber(i));

		/*
		 * INaturalNumberList s2=new NaturalNumberList("y",new long[] { 234, 23, 123,
		 * 512 });
		 */

		INaturalNumberList s2 = new NaturalNumberList("y", new long[s1.size()]);

		System.out.println("s2.getName() = " + s2.getName());
		for (int i = 0; i < s2.size(); i++)
			System.out.println("s2.getNaturalNumber(" + i + ") = " + s2.getNaturalNumber(i));

		IProblem bslf = new NaturalNumberListFixer(s1);

		IProblem bsls = new NaturalNumberListSorter(s1, s2);

		IProblem problem = new Conjunction(bslf, bsls);

		System.out.println(problem);

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		// List s=PartialSolution.solveList(problem);
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("s1=" + s1);
			System.out.println("s2=" + s2);
		}
		else
			System.out.println("No solution.");
	}
}
