package demos;

import java.util.List;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import bitstringlists.BitStringList;
import bitstringlists.BitStringListDisjointUnioner;
import bitstringlists.BitStringListFixer;
import bitstringlists.BitStringListSorter;
import bitstringlists.IBitStringList;
import bitstrings.BitString;

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

		IProblem problem = new Conjunction(slmfix, bslfix,
				new BitStringListDisjointUnioner(target, slm, bsl),
				new BitStringListSorter(target));

		System.out.println(problem);

		List<?> s = problem.findModel(Problem.defaultSolver());
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
