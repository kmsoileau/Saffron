package demos.bits.strings.lists;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.lists.BitStringList;
import bits.strings.lists.BitStringListDisjointUnioner;
import bits.strings.lists.BitStringListFixer;
import bits.strings.lists.BitStringListSorter;
import bits.strings.lists.IBitStringList;

/**
 * <p>
 * Title: BitStringListDisjointUnionerDemo
 * </p>
 * <p>
 * Description: TBS
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

public class BitStringListDisjointUnionerDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitStringList slm = new BitStringList("y", new boolean[][]
		{
				{ false, true, true },
				{ true, true, true }, });
		IProblem slmfix = new BitStringListFixer(slm);

		IBitStringList bsl = new BitStringList("slist", new IBitString[]
		{ new BitString(new boolean[]
				{ true, true, true }), new BitString(new boolean[]
				{ false, true, false }) });
		IProblem bslfix = new BitStringListFixer(bsl);

		IBitStringList target = new BitStringList("combined", new boolean[4][3]);

		IProblem problem = new Conjunction(slmfix, bslfix, new BitStringListDisjointUnioner(target, slm, bsl),
				new BitStringListSorter(target));

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
