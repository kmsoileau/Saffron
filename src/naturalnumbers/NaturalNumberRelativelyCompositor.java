package naturalnumbers;

import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

/**
*
* @author Kerry Michael Soileau
*         <p>
*         email: ksoileau2@yahoo.com
*         <p>
*         website: http://kerrysoileau.com/index.html
* @version 1.0
* @since Mar 4, 2019
*/
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
