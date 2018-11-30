package demos.bitstringlists;

import java.util.List;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.IProblem;
import bits.Problem;
import bitstringlists.BitStringList;
import bitstringlists.BitStringListDifferencer;
import bitstringlists.BitStringListFixer;
import bitstringlists.IBitStringList;

/**
 * <p>
 * Title: BitStringListDifferencerDemo
 * </p>
 * <p>
 * Description: This is a sample application showing the use of the
 * BitStringListDifferencer class.
 * </p>
 * <p>
 * Copyright (c) 2006
 * </p>
 * <p>
 * Company: Positronic Software
 * </p>
 * 
 * @author Kerry Michael Soileau
 * @version 1.0
 */

public class BitStringListDifferencerDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitStringList slm = new BitStringList("y", new boolean[][]
		{
		{ true, false, false },
		{ true, false, true },
		{ true, true, true }, });
		IProblem slmfix = new BitStringListFixer(slm);

		IBitStringList bsl = new BitStringList("slist", new boolean[][]
		{
		{ true, false, false },
		{ false, true, true },
		{ true, false, true }, });

		IProblem bslfix = new BitStringListFixer(bsl);

		IBitStringList target = new BitStringList("combined", new boolean[][]
		{
		{ false, false, false },
		{ false, false, false },
		{ false, false, false } });

		IProblem problem = new Conjunction(slmfix, bslfix,
				new BitStringListDifferencer(slm, bsl, target));

		problem.sort();

		System.out.println(problem);

		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());

		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println(slm);
			System.out.println(bsl);
			System.out.println(target);
		}
		else
			System.out.println("No solution.");
	}
}