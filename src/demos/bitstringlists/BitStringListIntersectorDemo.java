package demos.bitstringlists;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bitstringlists.BitStringList;
import bitstringlists.BitStringListFixer;
import bitstringlists.BitStringListIntersector;
import bitstringlists.IBitStringList;

/**
 * <p>
 * Title: BitStringListIntersectorDemo
 * </p>
 * <p>
 * Description: This is a sample application showing the use of the
 * BitStringListIntersector class.
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

public class BitStringListIntersectorDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitStringList slm = new BitStringList("y", new boolean[][]
		{
		{ true, false, false },
		{ true, false, true }, });
		IProblem slmfix = new BitStringListFixer(slm);

		IBitStringList bsl = new BitStringList("slist", new boolean[][]
		{
		{ true, false, true },
		{ true, true, false }, });
		IProblem bslfix = new BitStringListFixer(bsl);

		IBitStringList target = new BitStringList("combined", new boolean[][]
		{
		{ true, true, true },
		{ true, true, true } });

		IProblem problem = new Conjunction(slmfix, bslfix,
				new BitStringListIntersector(target, slm, bsl));

		System.out.println(problem);

		IProblemMessage s = problem.findModel(Problem.defaultSolver());

		if (s.getStatus() == IProblemMessage.SATISFIABLE
				&& s.getLiterals().size() > 0)
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