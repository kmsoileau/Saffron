package demos.bitstringlists;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bitstringlists.BitStringList;
import bitstringlists.BitStringListDisjointer;
import bitstringlists.BitStringListFixer;
import bitstringlists.IBitStringList;

/**
 * <p>
 * Title: BitStringListDisjointerDemo
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

public class BitStringListDisjointerDemo /** @todo Test this thoroughly */
{
	public static void main(String[] args) throws Exception
	{
		IBitStringList slm = new BitStringList("y", new boolean[][]
		{
		{ true, false, false },
		{ true, true, false }, });

		IBitStringList bsl = new BitStringList("slist", new boolean[][]
		{
		{ false, true, false },
		{ false, false, false },
		{ false, true, false },
		{ false, true, false }, });

		IProblem problem = new Conjunction(new BitStringListFixer(slm),
				new BitStringListFixer(bsl), new BitStringListDisjointer(bsl,
						slm));

		System.out.println(problem);
		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE
				&& s.getLiterals().size() > 0)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("slm= " + slm);
			System.out.println("bsl= " + bsl);
		}
		else
			System.out.println("No solution.");
	}
}