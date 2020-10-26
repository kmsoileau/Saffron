package demos.bits.strings.lists;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.lists.BitStringList;
import bits.strings.lists.BitStringListDifferencer;
import bits.strings.lists.BitStringListFixer;
import bits.strings.lists.IBitStringList;

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

		IProblem problem = new Conjunction(slmfix, bslfix, new BitStringListDifferencer(slm, bsl, target));

		System.out.println(problem);

		IProblemMessage s = problem.findModel(Problem.defaultSolver());

		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println(slm);
			System.out.println(bsl);
			System.out.println(target);
		}
		else
			System.out.println("No solution.");
	}
}