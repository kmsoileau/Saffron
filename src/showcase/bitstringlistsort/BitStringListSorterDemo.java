/*
 * BitStringListSorterDemo.java	1.0 05/05/04
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */
/**
 * <p>Title: BitStringListSorterDemo</p>
 * <p>Description: TBS</p>
 * <p>Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */

package showcase.bitstringlistsort;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.lists.BitStringList;
import bits.strings.lists.BitStringListFixer;
import bits.strings.lists.BitStringListSorter;
import bits.strings.lists.IBitStringList;

public class BitStringListSorterDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitStringList s1 = new BitStringList("x", new boolean[][]
		{
				{ true, false, true },
				{ false, true, false },
				{ true, false, false },
				{ false, true, false } });
		System.out.println("s1.getName() = " + s1.getName());
		for (int i = 0; i < s1.size(); i++)
			System.out.println("s1.getBitString(" + i + ") = " + s1.getBitString(i));

		IBitStringList s2 = new BitStringList("y", new boolean[][]
		{
				{ true, false, true },
				{ false, true, false },
				{ true, false, false },
				{ false, true, false } });
		System.out.println("s2.getName() = " + s2.getName());
		for (int i = 0; i < s2.size(); i++)
			System.out.println("s2.getBitString(" + i + ") = " + s2.getBitString(i));

		IProblem bslf = new BitStringListFixer(s1);

		IProblem bsls = new BitStringListSorter(s1, s2);

		IProblem problem = new Conjunction(bslf, bsls);

		System.out.println(problem);

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
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