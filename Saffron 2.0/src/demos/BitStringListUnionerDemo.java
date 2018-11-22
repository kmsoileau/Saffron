package demos;

import java.util.List;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.IProblem;
import bits.Problem;
import bitstringlists.BitStringList;
import bitstringlists.BitStringListFixer;
import bitstringlists.BitStringListUnioner;
import bitstringlists.IBitStringList;

/**
 * Copyright (c) 2005 Company: Positronic Software
 * 
 * @author Kerry Michael Soileau
 * @version 1.0
 */

public class BitStringListUnionerDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitStringList slm = new BitStringList("y", new boolean[][]
		{
		{ true, false, false },
		{ true, true, false }, });
		IProblem slmfix = new BitStringListFixer(slm);

		IBitStringList bsl = new BitStringList("slist", new boolean[][]
		{
		{ true, false, true }, });
		IProblem bslfix = new BitStringListFixer(bsl);

		IBitStringList target = new BitStringList("combined", new boolean[3][3]);

		IProblem problem = new Conjunction(slmfix, bslfix,
				new BitStringListUnioner(target, slm, bsl));

		System.out.println(problem);
		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("slm= " + slm);
			System.out.println("bsl= " + bsl);
			System.out.println("target= " + target);
		}
		else
			System.out.println("No solution.");
	}
}