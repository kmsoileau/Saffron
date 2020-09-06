package demos.bits.strings.lists;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.lists.BitStringList;
import bits.strings.lists.BitStringListFixer;
import bits.strings.lists.BitStringListUnioner;
import bits.strings.lists.IBitStringList;

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
		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("slm= " + slm);
			System.out.println("bsl= " + bsl);
			System.out.println("target= " + target);
		}
		else
			System.out.println("No solution.");
	}
}