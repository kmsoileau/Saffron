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

package demos.bitstringlists;

import java.util.List;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.IProblem;
import bits.Problem;
import bitstringlists.BitStringList;
import bitstringlists.BitStringListDeranger;
import bitstringlists.BitStringListFixer;
import bitstringlists.IBitStringList;

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

		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("s1=" + s1);
			System.out.println("s2=" + s2);
		}
		else
			System.out.println("No solution.");
	}
}