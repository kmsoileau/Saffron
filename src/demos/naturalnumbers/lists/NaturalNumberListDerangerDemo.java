/*
 * NaturalNumberListDerangerDemo.java	1.0 09/05/21
 *
 * Copyright 2009 Positronic Software.
 *
 *
 */
/**
 * <p>Title: NaturalNumberListDerangerDemo</p>
 * <p>Description: TBS</p>
 * <p>Copyright (c) 2009</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */

package demos.naturalnumbers.lists;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.lists.INaturalNumberList;
import naturalnumbers.lists.NaturalNumberList;
import naturalnumbers.lists.NaturalNumberListDeranger;
import naturalnumbers.lists.NaturalNumberListFixer;

public class NaturalNumberListDerangerDemo
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumberList s1 = new NaturalNumberList("x", new long[]
		{ 3, 1, 9, 11 });

		INaturalNumberList s2 = new NaturalNumberList("y", new long[]
		{ 3, 1, 9, 11 });

		IProblem bslf = new NaturalNumberListFixer(s1);
		IProblem bsls = new NaturalNumberListDeranger(s1, s2);

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