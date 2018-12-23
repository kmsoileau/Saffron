package naturalnumbers;

import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

public class NaturalNumberRelativelyCompositor extends Problem implements
		IProblem
{
	public NaturalNumberRelativelyCompositor(INaturalNumber M,
			INaturalNumber N, INaturalNumber CF) throws Exception
	{
		INaturalNumber Two = new NaturalNumber();
		INaturalNumber A = new NaturalNumber();
		INaturalNumber B = new NaturalNumber();
		INaturalNumber P = new NaturalNumber();

		IProblem p = new Conjunction(new NaturalNumberFixer(Two, 2),
				new NaturalNumberMultiplier(CF, A, M),
				new NaturalNumberMultiplier(CF, B, N), new NaturalNumberAdder(
						P, Two, CF));

		this.setClauses(p.getClauses());
	}
}
