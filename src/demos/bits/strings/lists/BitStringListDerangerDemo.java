/*
 * BitStringListDerangerDemo.java	1.0 09/05/20
 *
 * Copyright 2009 Positronic Software.
 *
 *
 */
/**
 * <p>Title: BitStringListDerangerDemo</p>
 * <p>Description: TBS</p>
 * <p>Copyright (c) 2009</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */

package demos.bits.strings.lists;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.lists.BitStringList;
import bits.strings.lists.BitStringListDeranger;
import bits.strings.lists.BitStringListFixer;
import bits.strings.lists.IBitStringList;

public class BitStringListDerangerDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitStringList s1 = new BitStringList("x", new boolean[][]
		{
				{ true, false, true, false },
				{ false, true, false, true },
				{ true, false, false, true },
				{ false, true, false, false } });

		IBitStringList s2 = new BitStringList("y", new boolean[][]
		{
				{ true, false, true, false },
				{ false, true, false, true },
				{ true, false, false, true },
				{ false, true, false, false } });

		IProblem bslf = new BitStringListFixer(s1);
		IProblem bsls = new BitStringListDeranger(s1, s2);

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